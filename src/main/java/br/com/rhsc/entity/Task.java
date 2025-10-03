package br.com.rhsc.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@DynamicUpdate
@Table(name = "task")
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tas_id")
	private Integer id;

	@Column(name = "tas_title", nullable = false)
	private String title;

	@Column(name = "tas_description", nullable = false)
	private String description;

	@Column(name = "tas_createdAt", nullable = false)
	private Date createdAt;

	@Column(name = "tas_finishLimit", nullable = false)
	private LocalDate finishLimit;

	@Column(name = "tas_finishedAt")
	private Date finishedAt;

	@Column(name = "tas_status", nullable = false)
	private Integer status;

	@Version
	@Column(name = "tas_version", nullable = false)
	private Date version;

	@ManyToOne
	@JoinColumn(name = "tas_category", nullable = false, foreignKey = @ForeignKey(name = "task_category_01"))
	private Category category;
	
	public Task() {
		
	}

	public Task(String title, String description, Date createdAt, LocalDate finishLimit,
			Date finishedAt, Integer status, Date version) {
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.finishLimit = finishLimit;
		this.finishedAt = finishedAt;
		this.status = status;
		this.version = version;
	}

	public Task(String title, String description, Date createdAt, LocalDate finishLimit,
			Date finishedAt, Integer status, Date version, Category category ) {
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.finishLimit = finishLimit;
		this.finishedAt = finishedAt;
		this.status = status;
		this.version = version;
//		this.categories = categories;
		this.category = category;
	}

	public Task(Integer id, String title, String description, Date createdAt, LocalDate finishLimit,
			Date finishedAt, Integer status, Date version) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.finishLimit = finishLimit;
		this.finishedAt = finishedAt;
		this.status = status;
		this.version = version;
	}
	
	public Task(Builder build) {
		this.id = build.id;
		this.createdAt = build.createdAt;
		this.description = build.description;
		this.finishedAt = build.finishedAt;
		this.finishLimit = build.finishLimit;
		this.status = build.status;
		this.title = build.title;
		this.category = build.category;
		this.version = build.version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getFinishLimit() {
		return finishLimit;
	}

	public void setFinishLimit(LocalDate finishLimit) {
		this.finishLimit = finishLimit;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}
	
	public static class Builder {
		private Integer id;
		private String title;
		private String description;
		private Date createdAt;
		private LocalDate finishLimit;
		private Date finishedAt;
		private Integer status;
		private Date version;
		private Category category;
		
		public Task build() {
			return new Task(this);
		}
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public Builder setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public Builder setFinishLimit(LocalDate finishLimit) {
			this.finishLimit = finishLimit;
			return this;
		}
		
		public Builder setFinishedAt(Date finishedAt) {
			this.finishedAt = finishedAt;
			return this;
		}
		
		public Builder setStatus(Integer status) {
			this.status = status;
			return this;
		}
		
		public Builder setCategory(Category category) {
			this.category = category;
			return this;
		}
		
		public Builder setVersion(Date version) {
			this.version = version;
			return this;
		}
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", createdAt=" + createdAt
				+ ", finishLimit=" + finishLimit + ", finishedAt=" + finishedAt + ", status=" + status + ", version="
				+ version + ", category=" + category + "]";
	}
}
