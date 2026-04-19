package com.example.demo;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class DropboxService {

    private static final String ACCESS_TOKEN = "sl.u.AF-JFeyVQDNKgApyTaBjV6OyFLpNbKHiXtnxXekgimBtFaj6mo6dXxoe-ky8G-y-dPOktzr80Bw6S90kgjd7GsxJzX9E3RwmBE33Y5otnI0xj1cyu3X2hm3ru0YyCY-k-r5Gm1vbwX7K_0ci-q61AlXVjMNhu0LCLC0tl1qVrVqSBwMXZQcpb83Oa7gguIUARpRRF3wSSOdzNCx2yzOWXSOkZ8wUNS_xea2-zQPP2sUuTAammpT2dDE49q44sNfpjQQFvz0YoOcaB3b06Xue9hmPjAfpr9Inbxahi01FSoZSL93eUEw6pS0Zg_6r98NFg0zy9mCLoavUxOYiO1ScuoWon_5tYPNLjnVqqNu46wkoxliOUhI0xFjuAlCZuonUl11fZ4QMWq2CqjFZQvsmhaURbs9xwGKv-d-L8zkYfEKeUpQkxxTMMrJcOBGf_92OgvJLCDZQ-08hBNLMlut22BSt_50TqnilRl9adqGvfiRKLVHa3KCDYaxwDCSL1tuPw7lHHwzfVWpZ-DlyMFfUuq1Dl9eOIBeRVfkyjm6jjktkJK5Gf5m0X0aOIzC8VEcUtUtGt9Gtvb547YlpBJSZYHdcUvDX40zbgt1HiR_RIt42SKskOVwJgw-tnXzTrdS5YPFQAkC3LY7u-7DYj7EF4tCg8h1F8GqzEKnasvRjq262rl-jBBTnLkqc2DXE6I9tiYZ35cjewfDxRa3C4lZESQT84I3fNoLdYX5DWXCXhRc3uBv1A0rgfh1yXA8VKpyQwCrm9Jw3PJ6CQU77M3tJRVjLX0GpUhY7doVcH6cwfeKeO1USAEfh0rSo-qeUhk0gPdvkw_nFRQvEN3Y9AuOt8qBYFViM6cY2WBVWDRbYPAZ8Br8FWtbvcd7tL6i18z0Mk-1tAOWkwUQQhuROw5qSZt-hxHggY6zQHoy0gC39AL-w43-6ra-JgFISFWmGsI-4t6WkxsDDB3H9wcwfsaf5Jdi50uU7wjsgj60ktD1DJVqD5OobCGmfZ8xiYBxqad8vrFgh9D94m_vtRvy0Kh3xQhmygK3vLNSeVX9ffDyG3LnGWb7VKKwXmcL7igfRqkW70ex32EdPZaF-r5hngENBvYztvyRpf_DsTQFRhqzenkGEN8mrM_Z8f56TUhw_TrWdZS1AUviutKK54xL2LUxxyR_hMatOsSmKFpn-VfpL_dz2Kctj3iz4n-ueqBgfOCEMBdVtM-dygenLZAuLvG3nsVwLKY2iHovh-bjF0WF0sBOxocMAgLDaZ_lQ96yHEscBpjD0FccjQBeu_QVUrZBX02mRatDeRUK7TxjAYefl4PwksE0khn0bcEkoJeOw96NkObbCwDfYML1rmqLrOn1OCCymoJ1xYyWjhC8_jRg-ksgFD3cmvxGDIBfzfG2ChFEBSajKY7uwBFXKCT56oIMWp5Ul5bZt2wHrySd7Tq2vVq-TOA";
    private final DbxClientV2 client;

    public DropboxService() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-integration").build();
        this.client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public String uploadFileAndGetLink(InputStream in, String fileName) throws Exception {
        ListFolderResult result = client.files().listFolder("");
        for (Metadata md : result.getEntries()) {
            if (md.getName().equalsIgnoreCase(fileName)) {
                return "File already exists";
            }
        }

        FileMetadata metadata = client.files()
                .uploadBuilder("/" + fileName)
                .withMode(WriteMode.ADD)
                .uploadAndFinish(in);

        return getSharedLink(metadata.getPathLower());
    }

    public String uploadFileAndGetLinkForUpdate(InputStream in, String fileName) throws Exception {
        String path = "/" + fileName;

        FileMetadata metadata = client.files()
                .uploadBuilder(path)
                .withMode(WriteMode.OVERWRITE)
                .uploadAndFinish(in);

        return getSharedLink(metadata.getPathLower());
    }

    private String getSharedLink(String dropboxPath) throws Exception
    {
        try
        {
            SharedLinkMetadata linkMetadata = client.sharing()
                    .createSharedLinkWithSettings(dropboxPath);
            return linkMetadata.getUrl();
        }
        catch (CreateSharedLinkWithSettingsErrorException e)
        {
            if (e.errorValue.isSharedLinkAlreadyExists())
            {
                List<SharedLinkMetadata> links = client.sharing()
                        .listSharedLinksBuilder()
                        .withPath(dropboxPath)
                        .withDirectOnly(true)
                        .start()
                        .getLinks();
                if (!links.isEmpty())
                {
                    return links.get(0).getUrl();
                }
            }
            throw e;
        }
    }

    public void deleteFileByPath(String dropboxPathOrLink) throws DbxException {
        if (dropboxPathOrLink == null || dropboxPathOrLink.trim().isEmpty()) {
            throw new DbxException("Dropbox path is empty, cannot delete file.");
        }

        String candidate = dropboxPathOrLink.trim();

        if (candidate.startsWith("http")) {
            Pattern p = Pattern.compile("https?://[^\\s\"']+");
            Matcher m = p.matcher(candidate);
            if (m.find()) {
                candidate = m.group(0);
            }
            try {
                SharedLinkMetadata meta = client.sharing().getSharedLinkMetadata(candidate);
                String pathLower = meta.getPathLower();
                if (pathLower == null || pathLower.isEmpty()) {
                    throw new DbxException("Cannot resolve Dropbox path from shared link. Token may not own the file.");
                }
                candidate = pathLower;
            } catch (DbxException ex) {
                throw new DbxException("Failed to resolve shared link to path: " + ex.getMessage());
            }
        }

        if (!candidate.startsWith("/")) {
            throw new DbxException("Resolved value is not a Dropbox path starting with '/': " + candidate);
        }

        client.files().deleteV2(candidate);
        System.out.println("Deleted Dropbox file: " + candidate);
    }
}
