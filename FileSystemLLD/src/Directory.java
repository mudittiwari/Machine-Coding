import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem{
    private String directoryName;
    private List<FileSystem> directoryList;

    public Directory(String directoryName){
        this.directoryName = directoryName;
        this.directoryList = new ArrayList<>();
    }

    public void addFileSystem(FileSystem fileSystem){
        this.directoryList.add(fileSystem);
    }

    @Override
    public void ls(){
        System.out.println("LOGGING DIRECTORY INFO: " + directoryName);
        directoryList.forEach(directory -> {directory.ls();});
    }
}
