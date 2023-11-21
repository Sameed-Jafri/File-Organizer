import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileOrganizer {

    public static void main(String[] args) {
        String desktopPath = "C:/Users/Sameed Jafri/Desktop"; // desktop path
        Map<String, String> fileDestinations = new HashMap<>();
        
        // Define destination folders for file types
        fileDestinations.put("jpg", "Documents/images");
        fileDestinations.put("png", "Documents/images");
        fileDestinations.put("pdf", "Documents/pdf's");
        fileDestinations.put("txt", "Documents/notepad");
        // Add more mappings as needed

        File desktop = new File(desktopPath);
        File[] files = desktop.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileExtension = getFileExtension(file);
                    String destinationFolder = fileDestinations.get(fileExtension);

                    if (destinationFolder != null) {
                        new File(desktopPath + "/" + destinationFolder).mkdirs();
                        try {
                            Files.move(file.toPath(), Paths.get(desktopPath + "/" + destinationFolder + "/" + file.getName()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            e.getMessage();
                        }
                    }
                }
            }
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
