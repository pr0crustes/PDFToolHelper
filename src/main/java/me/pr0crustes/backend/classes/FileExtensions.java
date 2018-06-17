package me.pr0crustes.backend.classes;

public enum FileExtensions {

    PDF("pdf"),
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg"),
    DOC("doc"),
    DOCX("docx");

    private final String value;

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
