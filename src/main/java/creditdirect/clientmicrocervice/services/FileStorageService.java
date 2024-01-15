package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.config.FileStorageProperties;
import creditdirect.clientmicrocervice.entities.AttachedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.boot.model.internal.BinderHelper.getRelativePath;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            // Handle directory creation exception
        }
    }

    public List<AttachedFile> storeFilesForDossier(MultipartFile[] files, Long dossierId) {
        List<AttachedFile> attachedFiles = new ArrayList<>();
        Path dossierDirectory = this.fileStorageLocation.resolve(String.valueOf(dossierId));


        if (!Files.exists(dossierDirectory)) {
            try {
                Files.createDirectory(dossierDirectory);
            } catch (IOException ex) {
                // Handle directory creation exception
            }
        }

        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileUuid = UUID.randomUUID().toString();
            String filePath = dossierDirectory.resolve(fileUuid + "-" + fileName).toString();

            // Ensure the file name and path have ".pdf" extension
            fileName = addPdfExtension(fileName);
            filePath = addPdfExtension(filePath);

            try {
                Files.copy(file.getInputStream(), Paths.get(filePath));
                AttachedFile attachedFile = new AttachedFile();
                attachedFile.setFileName(fileName);
                attachedFile.setFilePath(getRelativePath(filePath)); // Use relative path
                attachedFile.setFileType("pdf"); // Ensure file type is set to "pdf"

                attachedFiles.add(attachedFile);
            } catch (IOException ex) {
                // Handle file storage exception
            }
        }

        return attachedFiles;
    }

    private String getRelativePath(String filePath) {
        String fileStorageLocationString = fileStorageLocation.toString();

        if (filePath.startsWith(fileStorageLocationString)) {
            return filePath.substring(fileStorageLocationString.length());
        } else {
            // Handle the case where the provided filePath does not start with fileStorageLocation
            return filePath;
        }
    }

    private String addPdfExtension(String fileNameOrPath) {
        if (!fileNameOrPath.toLowerCase().endsWith(".pdf")) {
            fileNameOrPath += ".pdf";
        }
        return fileNameOrPath;
    }
}
