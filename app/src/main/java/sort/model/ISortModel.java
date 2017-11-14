package sort.model;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public interface ISortModel {
    void getUrl(String url);
    void getRightUrl(String url, Map<String,String> mmap);
}
