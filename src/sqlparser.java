/* Generated By:JavaCC: Do not edit this line. sqlparser.java */
import java.util.ArrayList;

public class sqlparser implements sqlparserConstants {
  // define static variable
  public static final int PRINT_SYNTAX_ERROR = 0;
  public static final int PRINT_CREATE_TABLE = 1;
  public static final int PRINT_DROP_TABLE = 2;
  public static final int PRINT_DESC_TABLE = 3;
  public static final int PRINT_INSERT_TABLE = 4;
  public static final int PRINT_DELETE_TABLE = 5;
  public static final int PRINT_SELECT_TABLE = 6;
  public static final int PRINT_SHOW_TABLE = 7;

  public static void handleQuery(Query q) throws Exception, MyException
  {
    q.execute();
    System.out.print("DB_2009-13389> ");
  }

  static final public void command() throws ParseException, Exception, MyException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE_TABLE:
    case DROP_TABLE:
    case INSERT_INTO:
    case DESC:
    case DELETE_FROM:
    case SELECT:
    case SHOW_TABLES:
      queryList();
      break;
    case EXIT:
      jj_consume_token(EXIT);
      jj_consume_token(SEMICOLON);
      System.exit(0);
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void queryList() throws ParseException, Exception, MyException {
    Query q;
    label_1:
    while (true) {
      q = query();
      jj_consume_token(SEMICOLON);
      if(q != null) {
        handleQuery(q);
     }else {
                System.out.println("Unsupported Query");
                System.out.printf("DB_2009-13389> ");
     }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CREATE_TABLE:
      case DROP_TABLE:
      case INSERT_INTO:
      case DESC:
      case DELETE_FROM:
      case SELECT:
      case SHOW_TABLES:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
    }
  }

// query is createtable||droptable||desc||insert||delete||select||showtable query
  static final public Query query() throws ParseException, Exception, MyException {
  Query q;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CREATE_TABLE:
      q = createTableQuery();
             {if (true) return q;}
      break;
    case DROP_TABLE:
      q = dropTableQuery();
                {if (true) return q;}
      break;
    case DESC:
      q = descQuery();
       {if (true) return q;}
      break;
    case INSERT_INTO:
      q = insertQuery();
       {if (true) return q;}
      break;
    case DELETE_FROM:
      q = deleteQuery();
       {if (true) return q;}
      break;
    case SELECT:
      q = selectQuery();
       {if (true) return q;}
      break;
    case SHOW_TABLES:
      q = showTableQuery();
       {if (true) return q;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public CreateTableQuery createTableQuery() throws ParseException, Exception, MyException {
  String name;
  CreateTableQuery query;
    jj_consume_token(CREATE_TABLE);
    name = tableName();
    query = new CreateTableQuery(name);
    tableElementList(query);
        {if (true) return query;}
    throw new Error("Missing return statement in function");
  }

  static final public Query dropTableQuery() throws ParseException {
  String t;
    jj_consume_token(DROP_TABLE);
    t = tableName();
        DropTableQuery query = new DropTableQuery(t);
        {if (true) return query;}
    throw new Error("Missing return statement in function");
  }

  static final public DescQuery descQuery() throws ParseException, MyException, Exception {
  String t;
    jj_consume_token(DESC);
    t = tableName();
    DescQuery query = new DescQuery(t);
    {
      {if (true) return query;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public void tableElementList(CreateTableQuery q) throws ParseException, MyException {
    jj_consume_token(LEFT_PAREN);
    tableElement(q);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      tableElement(q);
    }
    jj_consume_token(RIGHT_PAREN);
  }

  static final public void tableElement(CreateTableQuery q) throws ParseException, MyException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LEGAL_IDENTIFIER:
      columnDefinition(q);
      break;
    case PRIMARY_KEY:
    case FOREIGN_KEY:
      tableConstraintDefinition(q);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void columnDefinition(CreateTableQuery q) throws ParseException, MyException {
  boolean notnull = false;
  String colname;
  DataType type;
    colname = columnName();
    type = dataType();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT_NULL:
      jj_consume_token(NOT_NULL);
      notnull = true;
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    q.addColumn(new Column(colname, type, notnull));
  }

  static final public void tableConstraintDefinition(CreateTableQuery q) throws ParseException, MyException {
  PrimaryKeyConstraint a;
  ReferentialConstraint b;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PRIMARY_KEY:
      a = primaryKeyConstraint();
            q.addPrimaryKeyConst(a);
      break;
    case FOREIGN_KEY:
      b = referentialConstraint();
                q.addReferentialKeyConst(b);
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public PrimaryKeyConstraint primaryKeyConstraint() throws ParseException, MyException {
        ArrayList<String > list;
    jj_consume_token(PRIMARY_KEY);
    list = columnNameList();
    {if (true) return new PrimaryKeyConstraint(list);}
    throw new Error("Missing return statement in function");
  }

  static final public ReferentialConstraint referentialConstraint() throws ParseException, MyException {
  ArrayList<String > list;
  ArrayList<String > namelist;
  String colname;
    jj_consume_token(FOREIGN_KEY);
    list = columnNameList();
    jj_consume_token(REFERENCES);
    colname = tableName();
    namelist = columnNameList();
        {if (true) return new ReferentialConstraint(list, colname, namelist);}
    throw new Error("Missing return statement in function");
  }

  static final public ArrayList<String > columnNameList() throws ParseException {
  ArrayList<String > list = new ArrayList<String >();
  String s;
    jj_consume_token(LEFT_PAREN);
    s = columnName();
    list.add(s);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      s = columnName();
        list.add(s);
    }
    jj_consume_token(RIGHT_PAREN);
    {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  static final public DataType dataType() throws ParseException, MyException {
  Token length;
  DataType type;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
                                type = new DataType(0);
      break;
    case CHAR:
      jj_consume_token(CHAR);
      jj_consume_token(LEFT_PAREN);
      length = jj_consume_token(INT_VALUE);
      jj_consume_token(RIGHT_PAREN);
             type = new DataType(1, (Integer.parseInt(length.toString())));
      break;
    case DATE:
      jj_consume_token(DATE);
                  type = new DataType(2);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        {if (true) return type;}
    throw new Error("Missing return statement in function");
  }

  static final public String tableName() throws ParseException {
  Token t;
    t = jj_consume_token(LEGAL_IDENTIFIER);
    {if (true) return t.toString().toLowerCase();}
    throw new Error("Missing return statement in function");
  }

  static final public String columnName() throws ParseException {
  Token t;
    t = jj_consume_token(LEGAL_IDENTIFIER);
        {if (true) return t.toString().toLowerCase();}
    throw new Error("Missing return statement in function");
  }

// insert query consists of <INSERT_INTO > + tableName() + insertColumnsandSources()
  static final public Query insertQuery() throws ParseException {
    jj_consume_token(INSERT_INTO);
    tableName();
    insertColumnsandSources();
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

// insertColumnsandSources consists of [columnNameList()]? + valueList()
  static final public void insertColumnsandSources() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LEFT_PAREN:
      columnNameList();
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    valueList();
  }

//valueList() consists of <VALUES > + ( + value + [< COMMA >, value()]? + )
  static final public void valueList() throws ParseException {
    jj_consume_token(VALUES);
    jj_consume_token(LEFT_PAREN);
    value();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      jj_consume_token(COMMA);
      value();
    }
    jj_consume_token(RIGHT_PAREN);
  }

//define comparable value
  static final public void comparableValue() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT_VALUE:
      jj_consume_token(INT_VALUE);
      break;
    case CHAR_STRING:
      jj_consume_token(CHAR_STRING);
      break;
    case DATE_VALUE:
      jj_consume_token(DATE_VALUE);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//values are null or comparable value
  static final public void value() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NULL:
      jj_consume_token(NULL);
      break;
    case INT_VALUE:
    case CHAR_STRING:
    case DATE_VALUE:
      comparableValue();
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//select query consists of <SELECT > + selectList() + tableExpr()
  static final public Query selectQuery() throws ParseException {
    jj_consume_token(SELECT);
    selectList();
    tableExpr();
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

//select list consists of <STAR > | selectedColumn() + [<COMMA >,selectedColumn()]*	
  static final public void selectList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STAR:
      jj_consume_token(STAR);
      break;
    case LEGAL_IDENTIFIER:
      selectedColumn();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
        selectedColumn();
      }
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

// use LOOKAHEAD to avoid TOKEN ambiguity
// selectedColumn = (tableName+< PERIOD >)? + columnName() + (< AS >columnName)?
  static final public void selectedColumn() throws ParseException {
    if (jj_2_1(2)) {
      tableName();
      jj_consume_token(PERIOD);
    } else {
      ;
    }
    columnName();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AS:
      jj_consume_token(AS);
      columnName();
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
  }

// tableExpr = fromClause + (whereClause)?
  static final public void tableExpr() throws ParseException {
    fromClause();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHERE:
      whereClause();
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
  }

// fromClause = < FROM > + tableReflist()
  static final public void fromClause() throws ParseException {
    jj_consume_token(FROM);
    tableReferenceList();
  }

// tableReflist() = referedTable + (< COMMA > + referedTable )*
  static final public void tableReferenceList() throws ParseException {
    referedTable();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      referedTable();
    }
  }

// referedTable() = tableName + (< AS >tableName)?
  static final public void referedTable() throws ParseException {
    tableName();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AS:
      jj_consume_token(AS);
      tableName();
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
  }

// whereclause consists of <WHERE > + boolValueexpr
  static final public void whereClause() throws ParseException {
    jj_consume_token(WHERE);
    booleanValueExpr();
  }

//boolValueexpr = booleanTerm | booleanTerm <OR > booleanValueExpr
//Same as boolValueExpr = booleanTerm + (< OR > booleanTerm)*
//disassembling recursive term 
  static final public void booleanValueExpr() throws ParseException {
    booleanTerm();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_7;
      }
      jj_consume_token(OR);
      booleanTerm();
    }
  }

//booleanTerm = booleanfactor | booleanfactor <AND > booleanterm
//Same as booleanTerm = booleanfactor + (<AND> booleanfactor)*
//disassembling recursive term 
  static final public void booleanTerm() throws ParseException {
    booleanFactor();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[20] = jj_gen;
        break label_8;
      }
      jj_consume_token(AND);
      booleanFactor();
    }
  }

//booleanfactor = (< NOT >)? + booleanTest;
  static final public void booleanFactor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
      break;
    default:
      jj_la1[21] = jj_gen;
      ;
    }
    booleanTest();
  }

// booleantest = predicate | parenthesizedBoolexpr
  static final public void booleanTest() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LEGAL_IDENTIFIER:
    case INT_VALUE:
    case CHAR_STRING:
    case DATE_VALUE:
      predicate();
      break;
    case LEFT_PAREN:
      parenthesizedBoolExpr();
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

// parenthesizedBoolexpr = ( + booleanvalueExpr + )
  static final public void parenthesizedBoolExpr() throws ParseException {
    jj_consume_token(LEFT_PAREN);
    booleanValueExpr();
    jj_consume_token(RIGHT_PAREN);
  }

// use lookahead to prevent TOKEN ambiguity
// predicate = comparison Predicate | null Predicate
  static final public void predicate() throws ParseException {
    if (jj_2_2(2)) {
      comparisonPredicate();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LEGAL_IDENTIFIER:
        nullPredicate();
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

// comparison Predicate = comparion Operand + < COMP_OP > + comparison Operand
  static final public void comparisonPredicate() throws ParseException {
    compOperand();
    jj_consume_token(COMP_OP);
    compOperand();
  }

// comparison Operand is comparable value | ( (tableName() + < PERIOD >)? columnName)
// use lookahead for same reason to above
  static final public void compOperand() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT_VALUE:
    case CHAR_STRING:
    case DATE_VALUE:
      comparableValue();
      break;
    case LEGAL_IDENTIFIER:
      if (jj_2_3(2)) {
        tableName();
        jj_consume_token(PERIOD);
      } else {
        ;
      }
      columnName();
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

// null predicate = ( tableName()< PERIOD >)? + columnName() + nullOperation()
  static final public void nullPredicate() throws ParseException {
    if (jj_2_4(2)) {
      tableName();
      jj_consume_token(PERIOD);
    } else {
      ;
    }
    columnName();
    nullOperation();
  }

// null operation consists of <IS > + (< NOT >)? + < NULL >
  static final public void nullOperation() throws ParseException {
    jj_consume_token(IS);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(NULL);
  }

// deletequery = < DELETE_FROM > + tableName() + whereclause(optional)
  static final public Query deleteQuery() throws ParseException {
    jj_consume_token(DELETE_FROM);
    tableName();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHERE:
      whereClause();
      break;
    default:
      jj_la1[26] = jj_gen;
      ;
    }
        {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

// show table query is just <SHOW TABLES >
  static final public ShowTableQuery showTableQuery() throws ParseException, Exception {
  ShowTableQuery query;
    jj_consume_token(SHOW_TABLES);
    query = new ShowTableQuery();
    {if (true) return query;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(LEGAL_IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) jj_scanpos = xsp;
    if (jj_3R_15()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_3R_14()) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_3R_11()) return true;
    if (jj_scan_token(COMP_OP)) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(PERIOD)) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(PERIOD)) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(LEGAL_IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(50)) {
    jj_scanpos = xsp;
    if (jj_scan_token(52)) {
    jj_scanpos = xsp;
    if (jj_scan_token(53)) return true;
    }
    }
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(PERIOD)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public sqlparserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[27];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xfe20,0xfe00,0xfe00,0x0,0x60000,0x10000,0x60000,0x0,0x1c0,0x0,0x0,0x0,0x20000000,0x0,0x10000000,0x200000,0x800000,0x0,0x200000,0x2000000,0x1000000,0x4000000,0x0,0x0,0x0,0x4000000,0x800000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x2000,0x400,0x0,0x0,0x2000,0x0,0x800,0x2000,0x340000,0x340000,0x2000,0x400,0x0,0x0,0x2000,0x0,0x0,0x0,0x0,0x340c00,0x400,0x340400,0x0,0x0,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[4];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public sqlparser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public sqlparser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new sqlparserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public sqlparser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new sqlparserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public sqlparser(sqlparserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(sqlparserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[60];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 27; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 60; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 4; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
