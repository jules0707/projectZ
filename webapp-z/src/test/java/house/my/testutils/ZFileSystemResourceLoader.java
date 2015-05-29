package house.my.testutils;

import org.springframework.core.io.ContextResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

public class ZFileSystemResourceLoader extends FileSystemResourceLoader {

  @Override
  protected Resource getResourceByPath(String path) {
    return new FileSystemContextResource(path);
  }

  private static class FileSystemContextResource extends FileSystemResource implements ContextResource {

    public FileSystemContextResource(String path) {
      super(path);
    }

    public String getPathWithinContext() {
      return getPath();
    }
  }
}