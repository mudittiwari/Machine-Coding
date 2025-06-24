//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FileSystem directory = new Directory("main");
        FileSystem file = new File("test.java");
        ((Directory)directory).addFileSystem(file);
        directory.ls();
        FileSystem subDirectory = new Directory("submain");
        FileSystem subFile = new File("subFile.java");
        ((Directory)directory).addFileSystem(subDirectory);
        ((Directory)subDirectory).addFileSystem(subFile);
        directory.ls();
    }
}