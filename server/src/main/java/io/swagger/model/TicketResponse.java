package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TicketResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")


public class TicketResponse   {
  @JsonProperty("ticketId")
  private Integer ticketId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;


  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    OUVERT("OUVERT"),
    
    ASSIGNE("ASSIGNE"),
    
    VALIDATION("VALIDATION"),
    
    TERMINE("TERMINE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  /**
   * Gets or Sets priority
   */
  public enum PriorityEnum {
    HAUTE("haute"),
    
    MOYENNE("moyenne"),
    
    BASSE("basse");

    private String value;

    PriorityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PriorityEnum fromValue(String text) {
      for (PriorityEnum b : PriorityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("priority")
  private PriorityEnum priority = null;

  @JsonProperty("creationDate")
  private OffsetDateTime creationDate = null;

  @JsonProperty("updateDate")
  private OffsetDateTime updateDate = null;

  @JsonProperty("creatorId")
  private Integer creatorId = null;

  @JsonProperty("assignedAdminId")
  private Integer assignedAdminId = null;

  public TicketResponse ticketId(Integer ticketId) {
    this.ticketId = ticketId;
    return this;
  }

  /**
   * Get ticketId
   * @return ticketId
  **/
  @ApiModelProperty(example = "1", value = "")


  public Integer getTicketId() {
    return ticketId;
  }

  public void setTicketId(Integer ticketId) {
    this.ticketId = ticketId;
  }

  public TicketResponse title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "Bug dans l'application", value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TicketResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "L'application crash au démarrage", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TicketResponse status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "OUVERT", value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public TicketResponse priority(PriorityEnum priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
  **/
  @ApiModelProperty(example = "haute", value = "")


  public PriorityEnum getPriority() {
    return priority;
  }

  public void setPriority(PriorityEnum priority) {
    this.priority = priority;
  }

  public TicketResponse creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
  **/
  @ApiModelProperty(example = "2025-11-01T10:30:00Z", value = "")

  @Valid

  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public TicketResponse updateDate(OffsetDateTime updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * Get updateDate
   * @return updateDate
  **/
  @ApiModelProperty(example = "2025-11-01T10:30:00Z", value = "")

  @Valid

  public OffsetDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(OffsetDateTime updateDate) {
    this.updateDate = updateDate;
  }

  public TicketResponse creatorId(Integer creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  /**
   * Get creatorId
   * @return creatorId
  **/
  @ApiModelProperty(example = "1", value = "")


  public Integer getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }

  public TicketResponse assignedAdminId(Integer assignedAdminId) {
    this.assignedAdminId = assignedAdminId;
    return this;
  }

  /**
   * 0 si non assigné
   * @return assignedAdminId
  **/
  @ApiModelProperty(example = "0", value = "0 si non assigné")


  public Integer getAssignedAdminId() {
    return assignedAdminId;
  }

  public void setAssignedAdminId(Integer assignedAdminId) {
    this.assignedAdminId = assignedAdminId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketResponse ticketResponse = (TicketResponse) o;
    return Objects.equals(this.ticketId, ticketResponse.ticketId) &&
        Objects.equals(this.title, ticketResponse.title) &&
        Objects.equals(this.description, ticketResponse.description) &&
        Objects.equals(this.status, ticketResponse.status) &&
        Objects.equals(this.priority, ticketResponse.priority) &&
        Objects.equals(this.creationDate, ticketResponse.creationDate) &&
        Objects.equals(this.updateDate, ticketResponse.updateDate) &&
        Objects.equals(this.creatorId, ticketResponse.creatorId) &&
        Objects.equals(this.assignedAdminId, ticketResponse.assignedAdminId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketId, title, description, status, priority, creationDate, updateDate, creatorId, assignedAdminId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketResponse {\n");
    
    sb.append("    ticketId: ").append(toIndentedString(ticketId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    updateDate: ").append(toIndentedString(updateDate)).append("\n");
    sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
    sb.append("    assignedAdminId: ").append(toIndentedString(assignedAdminId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

