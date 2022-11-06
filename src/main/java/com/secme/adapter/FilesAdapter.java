/**
 * @author H.G. Malwatta - IT19240848
 * @version 1.0
 *
 * This interface is used to define the methods for the FileAdapter
 */

package com.secme.adapter;
import com.secme.dto.FileResponseDto;
import com.secme.model.File;
import java.util.List;

public interface FilesAdapter {
    File save(File file);
    File update(File file);
    FileResponseDto updateContent(String id, String newContent);
    FileResponseDto delete (String id);
    File getById (String id);
    List<File> getAllFiles();
    List<File> getByUsername (String username);
}
