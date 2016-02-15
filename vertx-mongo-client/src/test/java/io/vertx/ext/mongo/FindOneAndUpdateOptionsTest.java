package io.vertx.ext.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.test.core.TestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author <a href="mailto:hendrik.jander@futureofwebtechnology.com">Hendrik Jander</a>
 */
public class FindOneAndUpdateOptionsTest {

  @Test
  public void testToJsonWithOptionalValues() {

    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();

    JsonObject projection = randomJsonObject();
    JsonObject sort = randomJsonObject();
    boolean upsert = TestUtils.randomBoolean();
    long maxTimeMS = TestUtils.randomLong();
    FindOneAndUpdateOptions.ReturnDocument returnDocument = FindOneAndUpdateOptions.ReturnDocument.BEFORE;
    Boolean bypassDocumentValidation = TestUtils.randomBoolean();

    options.setProjection(projection);
    options.setSort(sort);
    options.setUpsert(upsert);
    options.setMaxTimeMS(maxTimeMS);
    options.setReturnDocument(returnDocument);
    options.setBypassDocumentValidation(bypassDocumentValidation);

    assertEquals(options, new FindOneAndUpdateOptions(options.toJson()));
  }

  @Test
  public void testToJsonWithoutOptionalValues() {

    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();

    JsonObject projection = randomJsonObject();
    JsonObject sort = randomJsonObject();
    long maxTimeMS = TestUtils.randomLong();

    options.setProjection(projection);
    options.setSort(sort);
    options.setMaxTimeMS(maxTimeMS);

    assertEquals(options, new FindOneAndUpdateOptions(options.toJson()));
  }

  @Test
  public void testOptions() {

    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();

    JsonObject projection = randomJsonObject();
    assertEquals(options, options.setProjection(projection));
    assertEquals(projection, options.getProjection());

    JsonObject sort = randomJsonObject();
    assertEquals(options, options.setSort(sort));
    assertEquals(sort, options.getSort());

    boolean upsert = TestUtils.randomBoolean();
    assertEquals(options, options.setUpsert(upsert));
    assertEquals(upsert, options.isUpsert());

    long maxTimeMS = TestUtils.randomLong();
    assertEquals(options, options.setMaxTimeMS(maxTimeMS));
    assertEquals(maxTimeMS, options.getMaxTimeMS());

    FindOneAndUpdateOptions.ReturnDocument returnDocument = FindOneAndUpdateOptions.ReturnDocument.AFTER;
    assertEquals(options, options.setReturnDocument(returnDocument));
    assertEquals(returnDocument, options.getReturnDocument());

    Boolean bypassDocumentValidation = TestUtils.randomBoolean();
    assertEquals(options, options.setBypassDocumentValidation(bypassDocumentValidation));
    assertEquals(bypassDocumentValidation, options.getBypassDocumentValidation());

  }

  @Test
  public void testDefaultOptions() {
    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();

    assertNull(options.getProjection());
    assertNull(options.getSort());
    assertNull(options.getBypassDocumentValidation());
    assertEquals(FindOneAndUpdateOptions.ReturnDocument.BEFORE, options.getReturnDocument());
    assertEquals(0, options.getMaxTimeMS());
    assertEquals(false, options.isUpsert());

  }

  @Test
  public void testOptionsJson() {
    JsonObject json = new JsonObject();

    JsonObject projection = randomJsonObject();
    json.put("projection", projection);

    JsonObject sort = randomJsonObject();
    json.put("sort", sort);

    boolean upsert = TestUtils.randomBoolean();
    json.put("upsert", upsert);

    FindOneAndUpdateOptions.ReturnDocument returnDocument = FindOneAndUpdateOptions.ReturnDocument.AFTER;
    json.put("returnDocument", returnDocument);

    long maxTimeMS = TestUtils.randomLong();
    json.put("maxTimeMS", maxTimeMS);

    boolean bypassDocumentValidation = TestUtils.randomBoolean();
    json.put("bypassDocumentValidation", bypassDocumentValidation);

    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions(json);
    assertEquals(projection, options.getProjection());
    assertEquals(sort, options.getSort());
    assertEquals(upsert, options.isUpsert());
    assertEquals(returnDocument, options.getReturnDocument());
    assertEquals(maxTimeMS, options.getMaxTimeMS());
    assertEquals(bypassDocumentValidation, options.getBypassDocumentValidation());
  }

  @Test
  public void testDefaultOptionsJson() {

    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions(new JsonObject());
    FindOneAndUpdateOptions def = new FindOneAndUpdateOptions();

    assertEquals(def.getProjection(), options.getProjection());
    assertEquals(def.getSort(), options.getSort());
    assertEquals(def.isUpsert(), options.isUpsert());
    assertEquals(def.getMaxTimeMS(), options.getMaxTimeMS());
    assertEquals(def.getReturnDocument(), options.getReturnDocument());
    assertEquals(def.getBypassDocumentValidation(), options.getBypassDocumentValidation());
  }

  @Test
  public void testCopyOptions() {

    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();

    JsonObject projection = randomJsonObject();
    JsonObject sort = randomJsonObject();
    boolean upsert = TestUtils.randomBoolean();
    FindOneAndUpdateOptions.ReturnDocument returnDocument = FindOneAndUpdateOptions.ReturnDocument.BEFORE;
    long maxTimeMS = TestUtils.randomLong();
    Boolean bypassDocumentValidation = TestUtils.randomBoolean();

    options.setProjection(projection);
    options.setSort(sort);
    options.setUpsert(upsert);
    options.setReturnDocument(returnDocument);
    options.setMaxTimeMS(maxTimeMS);
    options.setBypassDocumentValidation(bypassDocumentValidation);

    FindOneAndUpdateOptions copy = new FindOneAndUpdateOptions(options);
    assertEquals(options.getProjection(), copy.getProjection());
    assertEquals(options.getSort(), copy.getSort());
    assertEquals(options.isUpsert(), copy.isUpsert());
    assertEquals(options.getReturnDocument(), copy.getReturnDocument());
    assertEquals(options.getMaxTimeMS(), copy.getMaxTimeMS());
    assertEquals(options.getBypassDocumentValidation(), copy.getBypassDocumentValidation());
  }

  private static JsonObject randomJsonObject() {
    JsonObject json = new JsonObject();
    json.put("string", TestUtils.randomAlphaString(10));
    json.put("int", TestUtils.randomInt());
    json.put("boolean", TestUtils.randomBoolean());

    return json;
  }
}