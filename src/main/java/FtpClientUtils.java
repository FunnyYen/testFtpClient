import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtils {
    public static void main(String[] args) {
        String server = "127.0.0.1";
        int port = 21;
        String user = "jack";
        String pass = "Funnyw53401";

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            ftpClient.sendCommand("opts", "hash md5");
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("hash", "windows-kb841290-x86-enu.exe")))
            {
                String reply = ftpClient.getReplyString();
                System.out.println(reply);
            }

            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
