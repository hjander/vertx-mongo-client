package io.vertx.ext.mongo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Options used to configure findOneAndUpdate operations.
 * Mirrors the mongo async drivers {@link com.mongodb.client.model.FindOneAndUpdateOptions}
 *
 * @since 3.3.0
 * @author <a href="mailto:hendrik.jander@futureofwebtechnology.com">Hendrik Jander</a>
 */
@DataObject
public class FindOneAndUpdateOptions {

  private JsonObject projection;
  private JsonObject sort;
  private boolean upsert;
  private ReturnDocument returnDocument;
  private long maxTimeMS;
  private Boolean bypassDocumentValidation;

  /**
   * The default value of returnDocument = ReturnDocument.BEFORE, signifying the document is returned before the update
   */
  public static final ReturnDocument DEFAULT_RETURN_DOCUMENT = ReturnDocument.BEFORE;

  public FindOneAndUpdateOptions() {
    returnDocument = DEFAULT_RETURN_DOCUMENT;
  }

  /**
   * Indicates which document to return, the original document before change or the document after the change
   */
  public enum ReturnDocument {
    /**
     * Indicates to return the document before the update, replacement, or insert occurred.
     */
    BEFORE,
    /**
     * Indicates to return the document after the update, replacement, or insert occurred.
     */
    AFTER
  }

  /**
   * Copy constructor
   *
   * @param other the one to copy
   */
  public FindOneAndUpdateOptions(FindOneAndUpdateOptions other) {
    this.projection = other.projection;
    this.sort = other.sort;
    this.upsert = other.upsert;
    this.returnDocument = other.returnDocument;
    this.maxTimeMS = other.maxTimeMS;
    this.bypassDocumentValidation = other.bypassDocumentValidation;
  }

  /**
   * Constructor from JSON
   *
   * @param json the JSON
   */
  public FindOneAndUpdateOptions(JsonObject json) {
    this.projection = json.getJsonObject("projection");
    this.sort = json.getJsonObject("sort");
    this.upsert = json.getBoolean("upsert", Boolean.FALSE);
    this.returnDocument = ReturnDocument.valueOf(json.getString("returnDocument", DEFAULT_RETURN_DOCUMENT.name()));
    this.maxTimeMS = json.getLong("maxTimeMS", 0L);
    if (json.getBoolean("bypassDocumentValidation") != null) {
      this.bypassDocumentValidation = json.getBoolean("bypassDocumentValidation");
    }
  }

  /**
   * Convert to JSON
   *
   * @return the JSON
   */
  public JsonObject toJson() {
    JsonObject json = new JsonObject();

    if (projection != null) {
      json.put("projection", projection);
    }
    if (sort != null) {
      json.put("sort", sort);
    }

    json.put("upsert", upsert);
    json.put("returnDocument", returnDocument);
    json.put("maxTimeMS", maxTimeMS);

    if (bypassDocumentValidation != null) {
      json.put("bypassDocumentValidation", bypassDocumentValidation);
    }

    return json;
  }

  /**
   * Get the projection - describing the fields to return for all matching documents
   *
   * @return the projection
   */
  public JsonObject getProjection() {
    return projection;
  }

  /**
   * Set the projection
   *
   * @param projection  the projection - describing the fields to return for all matching documents
   * @return reference to this, for fluency
   */
  public FindOneAndUpdateOptions setProjection(JsonObject projection) {
    this.projection = projection;
    return this;
  }

  /**
   * Get the sort - sort criteria to apply to the query
   *
   * @return the sort
   */
  public JsonObject getSort() {
    return sort;
  }

  /**
   * Set the sort
   *
   * @param sort  the sort - sort criteria to apply to the query
   * @return reference to this, for fluency
   */
  public FindOneAndUpdateOptions setSort(JsonObject sort) {
    this.sort = sort;
    return this;
  }

  /**
   * Get the upsert - if a new document should be inserted if there are no matches to the query filter
   *
   * @return the upsert
   */
  public boolean isUpsert() {
    return upsert;
  }

  /**
   * Set the upsert
   *
   * @param upsert  the upsert - if a new document should be inserted if there are no matches to the query filter
   * @return reference to this, for fluency
   */
  public FindOneAndUpdateOptions setUpsert(boolean upsert) {
    this.upsert = upsert;
    return this;
  }

  /**
   * Get the returnDocument - indicating whether to return the document before it was updated / inserted or after
   *
   * @return the returnDocument
   */
  public ReturnDocument getReturnDocument() {
    return returnDocument;
  }

  /**
   * Set the returnDocument
   *
   * @param returnDocument  the returnDocument - indicating whether to return the document before it was updated / inserted or after
   * @return reference to this, for fluency
   */
  public FindOneAndUpdateOptions setReturnDocument(ReturnDocument returnDocument) {
    this.returnDocument = returnDocument;
    return this;
  }

  /**
   * Get the maxTimeMS - maximum execution time for the find one and update operation
   *
   * @return the maxTimeMS
   */
  public long getMaxTimeMS() {
    return maxTimeMS;
  }

  /**
   * Set the maxTimeMS
   *
   * @param maxTimeMS  the maxTimeMS - maximum execution time for the find one and update operation
   * @return reference to this, for fluency
   */
  public FindOneAndUpdateOptions setMaxTimeMS(long maxTimeMS) {
    this.maxTimeMS = maxTimeMS;
    return this;
  }

  /**
   * Get the bypassDocumentValidation - allows the write to opt-out of document level validation
   *
   * @return the bypassDocumentValidation
   */
  public Boolean getBypassDocumentValidation() {
    return bypassDocumentValidation;
  }

  /**
   * Set the bypassDocumentValidation
   *
   * @param bypassDocumentValidation  the bypassDocumentValidation - allows the write to opt-out of document level validation
   * @return reference to this, for fluency
   */
  public FindOneAndUpdateOptions setBypassDocumentValidation(Boolean bypassDocumentValidation) {
    this.bypassDocumentValidation = bypassDocumentValidation;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FindOneAndUpdateOptions that = (FindOneAndUpdateOptions) o;

    if (upsert != that.upsert) return false;
    if (maxTimeMS != that.maxTimeMS) return false;
    if (projection != null ? !projection.equals(that.projection) : that.projection != null) return false;
    if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;
    if (returnDocument != that.returnDocument) return false;
    return !(bypassDocumentValidation != null ? !bypassDocumentValidation.equals(that.bypassDocumentValidation) : that.bypassDocumentValidation != null);

  }

  @Override
  public int hashCode() {
    int result = projection != null ? projection.hashCode() : 0;
    result = 31 * result + (sort != null ? sort.hashCode() : 0);
    result = 31 * result + (upsert ? 1 : 0);
    result = 31 * result + returnDocument.hashCode();
    result = 31 * result + (int) (maxTimeMS ^ (maxTimeMS >>> 32));
    result = 31 * result + (bypassDocumentValidation != null ? bypassDocumentValidation.hashCode() : 0);
    return result;
  }
}