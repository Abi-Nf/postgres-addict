package postgres.addict;

public class ColumnType {
  public static final String DETECT = "<detect>";

  /**
   * access control list
   */
  public static final String ACLITEM = "aclitem";

  /**
   * fixed-length bit string
   */
  public static final String BIT = "bit";

  /**
   * boolean, 'true'/'false'
   */
  public static final String BOOL = "bool";

  /**
   * boolean, 'true'/'false'
   */
  public static final String BOOLEAN = "boolean";

  /**
   * geometric box '(lower left,upper right)'
   */
  public static final String BOX = "box";

  /**
   * char(length), blank-padded string, fixed storage length
   */
  public static final String BPCHAR = "bpchar";

  /**
   * variable-length string, binary values escaped
   */
  public static final String BYTEA = "bytea";

  /**
   * single character
   */
  public static final String CHAR = "char";

  /**
   * command identifier type, sequence in transaction id
   */
  public static final String CID = "cid";

  /**
   * network IP address/netmask, network address
   */
  public static final String CIDR = "cidr";

  /**
   * geometric circle '(center,radius)'
   */
  public static final String CIRCLE = "circle";

  /**
   * date
   */
  public static final String DATE = "date";

  /**
   * single-precision floating point number, 4-byte storage
   */
  public static final String FLOAT4 = "float4";

  /**
   * double-precision floating point number, 8-byte storage
   */
  public static final String FLOAT8 = "float8";

  public static final String DOUBLE = "double precision";

  /**
   * GiST index internal text representation for text search
   */
  public static final String GTSVECTOR = "gtsvector";

  /**
   * IP address/netmask, host address, netmask optional
   */
  public static final String INET = "inet";

  /**
   * -32 thousand to 32 thousand, 2-byte storage
   */
  public static final String INT2 = "int2";

  public static final String INTEGER = "integer";

  /**
   * -2 billion to 2 billion integer, 4-byte storage
   */
  public static final String INT4 = "int4";

  /**
   * ~18 digit integer, 8-byte storage
   */
  public static final String INT8 = "int8";

  public static final String FLOAT = "float";

  /**
   * @ <number> <units>, time interval
   */
  public static final String INTERVAL = "interval";

  /**
   * JSON stored as text
   */
  public static final String JSON = "json";

  /**
   * Binary JSON
   */
  public static final String JSONB = "jsonb";

  /**
   * JSON path
   */
  public static final String JSONPATH = "jsonpath";

  /**
   * geometric line
   */
  public static final String LINE = "line";

  /**
   * geometric line segment '(pt1,pt2)'
   */
  public static final String LSEG = "lseg";

  /**
   * XX:XX:XX:XX:XX:XX, MAC address
   */
  public static final String MACADDR = "macaddr";

  /**
   * XX:XX:XX:XX:XX:XX:XX:XX, MAC address
   */
  public static final String MACADDR8 = "macaddr8";

  /**
   * monetary amounts, $d,ddd.cc
   */
  public static final String MONEY = "money";

  public static final String BIGINT = "bigint";

  /**
   * 63-byte type for storing system identifiers
   */
  public static final String NAME = "name";

  /**
   * numeric(precision, decimal), arbitrary precision number
   */
  public static final String NUMERIC = "numeric";

  /**
   * object identifier(oid), maximum 4 billion
   */
  public static final String OID = "oid";

  /**
   * geometric path '(pt1,...)'
   */
  public static final String PATH = "path";

  /**
   * BRIN bloom summary
   */
  public static final String PG_BRIN_BLOOM_SUMMARY = "pg_brin_bloom_summary";

  /**
   * BRIN minmax-multi summary
   */
  public static final String PG_BRIN_MINMAX_MULTI_SUMMARY = "pg_brin_minmax_multi_summary";

  /**
   * multivariate dependencies
   */
  public static final String PG_DEPENDENCIES = "pg_dependencies";

  /**
   * PostgreSQL LSN datatype
   */
  public static final String PG_LSN = "pg_lsn";

  /**
   * multivariate MCV list
   */
  public static final String PG_MCV_LIST = "pg_mcv_list";

  /**
   * multivariate ndistinct coefficients
   */
  public static final String PG_NDISTINCT = "pg_ndistinct";

  /**
   * string representing an internal node tree
   */
  public static final String PG_NODE_TREE = "pg_node_tree";

  /**
   * snapshot
   */
  public static final String PG_SNAPSHOT = "pg_snapshot";

  /**
   * geometric point '(x, y)'
   */
  public static final String POINT = "point";

  /**
   * geometric polygon '(pt1,...)'
   */
  public static final String POLYGON = "polygon";

  /**
   * reference to cursor (portal name)
   */
  public static final String REFCURSOR = "refcursor";

  /**
   * registered class
   */
  public static final String REGCLASS = "regclass";

  /**
   * registered collation
   */
  public static final String REGCOLLATION = "regcollation";

  /**
   * registered text search configuration
   */
  public static final String REGCONFIG = "regconfig";

  /**
   * registered text search dictionary
   */
  public static final String REGDICTIONARY = "regdictionary";

  /**
   * registered namespace
   */
  public static final String REGNAMESPACE = "regnamespace";

  /**
   * registered operator
   */
  public static final String REGOPER = "regoper";

  /**
   * registered operator (with args)
   */
  public static final String REGOPERATOR = "regoperator";

  /**
   * registered procedure
   */
  public static final String REGPROC = "regproc";

  /**
   * registered procedure (with args)
   */
  public static final String REGPROCEDURE = "regprocedure";

  /**
   * registered role
   */
  public static final String REGROLE = "regrole";

  /**
   * registered type
   */
  public static final String REGTYPE = "regtype";

  /**
   * variable-length string, no limit specified
   */
  public static final String TEXT = "text";

  /**
   * (block, offset), physical location of tuple
   */
  public static final String TID = "tid";

  /**
   * time of day
   */
  public static final String TIME = "time";

  /**
   * date and time
   */
  public static final String TIMESTAMP = "timestamp";

  /**
   * date and time with time zone
   */
  public static final String TIMESTAMPTZ = "timestamptz";

  /**
   * time of day with time zone
   */
  public static final String TIMETZ = "timetz";

  /**
   * query representation for text search
   */
  public static final String TSQUERY = "tsquery";

  /**
   * text representation for text search
   */
  public static final String TSVECTOR = "tsvector";

  /**
   * txid snapshot
   */
  public static final String TXID_SNAPSHOT = "txid_snapshot";

  /**
   * UUID datatype
   */
  public static final String UUID = "uuid";

  /**
   * variable-length bit string
   */
  public static final String VARBIT = "varbit";

  /**
   * varchar(length), non-blank-padded string, variable storage length
   */
  public static final String VARCHAR = "varchar";

  /**
   * transaction id
   */
  public static final String XID = "xid";

  /**
   * full transaction id
   */
  public static final String XID8 = "xid8";

  /**
   * XML content
   */
  public static final String XML = "xml";


  private ColumnType(){
    /* Prevent from instantiation */
  }
}