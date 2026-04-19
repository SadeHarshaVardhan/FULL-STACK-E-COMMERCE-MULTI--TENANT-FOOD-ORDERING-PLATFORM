package com.example.demo;

import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeaService {

    static final String url = "jdbc:oracle:thin:@localhost:1524/FREEPDB1";
    static final String user = "system";
    static final String pass = "MySecurePass123";

    private final DropboxService dropboxService;

    public TeaService(DropboxService dropboxService) {
        this.dropboxService = dropboxService;
    }

    public String indet(String teaname, String tealink, int teaprice, String teadet) throws Exception {
        String keep = "File already exists";
        String insert = "insert into tea(teaname,tealink,teaprice,teadet) values (?,?,?,?)";

        if (tealink.equals(keep)) {
            return "uploaded unsuccessfully - please change the file name";
        }

        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = c.prepareStatement(insert)) {
            pst.setString(1, teaname);
            pst.setString(2, tealink);
            pst.setInt(3, teaprice);
            pst.setString(4, teadet);

            if (pst.executeUpdate() > 0) {
                return "inserted successfully";
            }
        }
        return "not inserted successfully";
    }

    public List<Tea> getdet() throws Exception {
        List<Tea> l = new ArrayList<>();
        String getall = "select * from tea order by teaid asc";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             Statement st = c.createStatement();
             ResultSet rst = st.executeQuery(getall)) {
            while (rst.next()) {
                Tea t1 = new Tea(
                        rst.getInt("teaid"),
                        rst.getString("teaname"),
                        rst.getString("tealink"),
                        rst.getInt("teaprice"),
                        rst.getString("teadet"));
                l.add(t1);
            }
        }
        return l;
    }

    public String dodel(int id) throws Exception {
        String oldLink = null;

        String select = "SELECT tealink FROM tea WHERE teaid=?";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = c.prepareStatement(select)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    oldLink = rs.getString("tealink");
                } else {
                    return "Tea not found with id=" + id;
                }
            }
        }

        if (oldLink != null && !oldLink.trim().isEmpty()) {
            try {
                dropboxService.deleteFileByPath(oldLink);
            } catch (Exception e) {
                return "Failed to delete file from Dropbox: " + e.getMessage();
            }
        }

        String delete = "DELETE FROM tea WHERE teaid=?";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = c.prepareStatement(delete)) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                return "Deleted successfully";
            } else {
                return "Not deleted successfully";
            }
        }
    }

    public String doupdate(int teaid, String teaname, String link, int teaprice, String teadet) throws Exception {
        String oldLink = null;

        String select = "SELECT tealink FROM tea WHERE teaid=?";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = c.prepareStatement(select)) {
            pst.setInt(1, teaid);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    oldLink = rs.getString("tealink");
                } else {
                    return "Tea not found with id=" + teaid;
                }
            }
        }

        if (oldLink != null && link!=null &&!oldLink.equals("File already exists") && !oldLink.isEmpty()&&!oldLink.equals(link)) {
            try {
                dropboxService.deleteFileByPath(oldLink);
            } catch (Exception e) {
                System.err.println("Warning: Could not delete old Dropbox file → " + e.getMessage());
            }
        }
        if(link==null && oldLink!=null)
        {
           link=oldLink;
        }

        String update = "UPDATE tea SET teaname=?, tealink=?, teaprice=?, teadet=? WHERE teaid=?";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = c.prepareStatement(update)) {

            pst.setString(1, teaname);
            pst.setString(2, link);
            pst.setInt(3, teaprice);
            pst.setString(4, teadet);
            pst.setInt(5, teaid);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                return "Updated successfully";
            } else {
                return "No rows updated for id=" + teaid;
            }
        }
    }


}
