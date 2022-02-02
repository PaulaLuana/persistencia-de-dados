package model;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private int id;
    private String name;
    private String desc;
    private String file;
    private String data;
    private List<String> tags;

    public Article() {
        tags = new ArrayList<>();
    }

    public Article(int id, String name, String desc, String file, String data, List<String> tags) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.file = file;
        this.data = data;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", file='" + file + '\'' +
                ", data='" + data + '\'' +
                ", tags=" + tags +
                '}' + "\n";
    }
}
