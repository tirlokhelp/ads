package com.ks.ads.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

	private String fileUrl;

    private String fileType;

    @Lob
    private byte[] data; // Optional: if storing the image in the database
    
    public static class Builder {
        private String fileName;
        private String fileUrl;
        private String fileType;
        private byte[] data;

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public Builder fileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public Builder data(byte[] data) {
            this.data = data;
            return this;
        }

        public ImageEntity build() {
            ImageEntity entity = new ImageEntity();
            entity.fileName = this.fileName;
            entity.fileUrl = this.fileUrl;
            entity.fileType = this.fileType;
            entity.data = this.data;
            return entity;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
    public String getFileType() {
        return fileType;
    }

    public byte[] getData() {
        return data;
    }
    public String getFileName() {
  		return fileName;
  	}
  	public void setFileName(String fileName) {
  		this.fileName = fileName;
  	}
  	public String getFileUrl() {
  		return fileUrl;
  	}
  	public void setFileUrl(String fileUrl) {
  		this.fileUrl = fileUrl;
  	}
  	public void setFileType(String fileType) {
  		this.fileType = fileType;
  	}
}