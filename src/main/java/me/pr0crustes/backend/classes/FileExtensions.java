package me.pr0crustes.backend.classes;

public enum FileExtensions {

    PDF("pdf"),
    PNG("png"),
    JPG("jpg"),
    DOC("doc"),
    DOCX("docx");

    private String value;

    FileExtensions(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public String getFilterDescription() {
        return this.toString().toUpperCase() + "s Files";
    }

    public String getExtension() {
        return "*." + this.toString();
    }
}
