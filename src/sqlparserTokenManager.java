/* Generated By:JavaCC: Do not edit this line. sqlparserTokenManager.java */
import java.util.ArrayList;

/** Token Manager. */
public class sqlparserTokenManager implements sqlparserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x100000000000000L) != 0L)
            return 28;
         if ((active0 & 0x1ffefffffe0L) != 0L)
         {
            jjmatchedKind = 42;
            return 11;
         }
         return -1;
      case 1:
         if ((active0 & 0xa200000L) != 0L)
            return 11;
         if ((active0 & 0x1ffe5dfffe0L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 1;
            return 11;
         }
         return -1;
      case 2:
         if ((active0 & 0xefe0deffa0L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 2;
            }
            return 11;
         }
         if ((active0 & 0x11005010040L) != 0L)
            return 11;
         return -1;
      case 3:
         if ((active0 & 0xecc09e6a00L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 3;
            }
            return 11;
         }
         if ((active0 & 0x13204095a0L) != 0L)
            return 11;
         return -1;
      case 4:
         if ((active0 & 0x480800000L) != 0L)
            return 11;
         if ((active0 & 0xe8401e6a00L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 4;
            }
            return 11;
         }
         return -1;
      case 5:
         if ((active0 & 0xc0000e0000L) != 0L)
         {
            if (jjmatchedPos != 5)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 5;
            }
            return 11;
         }
         if ((active0 & 0x2c40106a00L) != 0L)
            return 11;
         return -1;
      case 6:
         if ((active0 & 0x80000L) != 0L)
         {
            if (jjmatchedPos != 6)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 6;
            }
            return 11;
         }
         if ((active0 & 0xc000060000L) != 0L)
            return 11;
         return -1;
      case 7:
         if ((active0 & 0x80000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 7;
            return 11;
         }
         return -1;
      case 8:
         if ((active0 & 0x80000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 8;
            return 11;
         }
         return -1;
      case 9:
         if ((active0 & 0x80000L) != 0L)
            return 11;
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 32:
         return jjStopAtPos(0, 4);
      case 39:
         return jjStartNfaWithStates_0(0, 56, 28);
      case 40:
         return jjStopAtPos(0, 43);
      case 41:
         return jjStopAtPos(0, 44);
      case 42:
         return jjStopAtPos(0, 28);
      case 44:
         return jjStopAtPos(0, 45);
      case 46:
         return jjStopAtPos(0, 49);
      case 59:
         return jjStopAtPos(0, 41);
      case 95:
         return jjStopAtPos(0, 46);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa1_0(0x1200000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa1_0(0x40000280L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa1_0(0x2100003500L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa1_0(0x8000440000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa1_0(0x1808000840L);
      case 75:
      case 107:
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa1_0(0x24010000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 80:
      case 112:
         return jjMoveStringLiteralDfa1_0(0x4000020000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa1_0(0x20000c000L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa1_0(0x480000000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x480100100L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x12000087000L);
      case 72:
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x200808080L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x1801000840L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x8004050000L);
      case 82:
      case 114:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(1, 25, 11);
         return jjMoveStringLiteralDfa2_0(active0, 0x4140420600L);
      case 83:
      case 115:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(1, 21, 11);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(1, 27, 11);
         break;
      case 85:
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000L);
      case 88:
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x480000000L);
      case 68:
      case 100:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(2, 24, 11);
         break;
      case 69:
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x40800200L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000020020L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x2020106000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x300408400L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000040000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x800001800L);
      case 84:
      case 116:
         if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 6;
            jjmatchedPos = 2;
         }
         else if ((active0 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 26;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x1000010100L);
      case 89:
      case 121:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 40, 11);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000200L);
      case 67:
      case 99:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(3, 12, 11);
         break;
      case 69:
      case 101:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(3, 8, 11);
         return jjMoveStringLiteralDfa4_0(active0, 0xa8000c6800L);
      case 76:
      case 108:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(3, 29, 11);
         return jjMoveStringLiteralDfa4_0(active0, 0x480000000L);
      case 77:
      case 109:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(3, 22, 11);
         return jjMoveStringLiteralDfa4_0(active0, 0x4000020000L);
      case 79:
      case 111:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 36, 11);
         break;
      case 80:
      case 112:
         if ((active0 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x400L);
      case 82:
      case 114:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(3, 7, 11);
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 84:
      case 116:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(3, 5, 11);
         break;
      case 85:
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      case 87:
      case 119:
         if ((active0 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 33;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa5_0(active0, 0x8400L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000020000L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L);
      case 69:
      case 101:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(4, 23, 11);
         else if ((active0 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x400100000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000040000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x800080800L);
      case 84:
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x2040002200L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         if ((active0 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 30;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active0, 0x82200L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000040000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000020000L);
      case 83:
      case 115:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(5, 20, 11);
         else if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(5, 34, 11);
         break;
      case 84:
      case 116:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(5, 14, 11);
         else if ((active0 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active0, 0x8c00L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa7_0(active0, 0x2a00L);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x8400L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 78:
      case 110:
         if ((active0 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 39;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active0, 0xc0000L);
      case 89:
      case 121:
         if ((active0 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 38;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa8_0(active0, 0x60000L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa8_0(active0, 0x8400L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x80000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa8_0(active0, 0x2000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x800L);
      case 76:
      case 108:
         if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(7, 16);
         break;
      case 84:
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa9_0(active0, 0x200L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa9_0(active0, 0x80000L);
      case 75:
      case 107:
         return jjMoveStringLiteralDfa9_0(active0, 0x60000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa9_0(active0, 0x8400L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa9_0(active0, 0x800L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa9_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 66:
      case 98:
         return jjMoveStringLiteralDfa10_0(active0, 0x200L);
      case 69:
      case 101:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(9, 10);
         return jjMoveStringLiteralDfa10_0(active0, 0x68000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x2000L);
      case 83:
      case 115:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(9, 19, 11);
         break;
      case 84:
      case 116:
         return jjMoveStringLiteralDfa10_0(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
static private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 76:
      case 108:
         return jjMoveStringLiteralDfa11_0(active0, 0x200L);
      case 77:
      case 109:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(10, 13);
         break;
      case 79:
      case 111:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(10, 11);
         break;
      case 83:
      case 115:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(10, 15);
         break;
      case 89:
      case 121:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(10, 17);
         else if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(10, 18);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
static private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 69:
      case 101:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(11, 9);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 28;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 28:
                  if ((0xffffff7b00000000L & l) != 0L)
                     jjCheckNAddTwoStates(6, 7);
                  else if (curChar == 39)
                  {
                     if (kind > 51)
                        kind = 51;
                  }
                  break;
               case 0:
                  if ((0xffffff7b00000000L & l) != 0L)
                  {
                     if (kind > 58)
                        kind = 58;
                  }
                  else if (curChar == 39)
                     jjCheckNAddTwoStates(6, 7);
                  if ((0xfc00ff7a00000000L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                  }
                  else if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 50)
                        kind = 50;
                     jjCheckNAddStates(0, 3);
                  }
                  if ((0x7000000000000000L & l) != 0L)
                  {
                     if (kind > 47)
                        kind = 47;
                  }
                  else if ((0x280000000000L & l) != 0L)
                  {
                     if (kind > 48)
                        kind = 48;
                     jjCheckNAdd(13);
                  }
                  else if (curChar == 33)
                     jjCheckNAdd(1);
                  if (curChar == 60)
                     jjCheckNAdd(1);
                  else if (curChar == 62)
                     jjCheckNAdd(1);
                  break;
               case 1:
                  if (curChar == 61 && kind > 47)
                     kind = 47;
                  break;
               case 2:
                  if (curChar == 62)
                     jjCheckNAdd(1);
                  break;
               case 3:
                  if (curChar == 60)
                     jjCheckNAdd(1);
                  break;
               case 4:
                  if (curChar == 33)
                     jjCheckNAdd(1);
                  break;
               case 5:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(6, 7);
                  break;
               case 6:
                  if ((0xffffff7b00000000L & l) != 0L)
                     jjCheckNAddTwoStates(6, 7);
                  break;
               case 7:
                  if (curChar == 39 && kind > 51)
                     kind = 51;
                  break;
               case 8:
                  if ((0xffffff7b00000000L & l) != 0L && kind > 58)
                     kind = 58;
                  break;
               case 9:
                  if ((0xfc00ff7a00000000L & l) != 0L && kind > 59)
                     kind = 59;
                  break;
               case 12:
                  if ((0x280000000000L & l) == 0L)
                     break;
                  if (kind > 48)
                     kind = 48;
                  jjCheckNAdd(13);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjCheckNAdd(13);
                  break;
               case 14:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAddStates(0, 3);
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 18:
                  if (curChar == 45)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 19:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 21:
                  if (curChar == 45)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) != 0L && kind > 53)
                     kind = 53;
                  break;
               case 24:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 25;
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 26:
                  if ((0x3ff000000000000L & l) != 0L && kind > 54)
                     kind = 54;
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) != 0L && kind > 55)
                     kind = 55;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 28:
               case 6:
                  if ((0x7fffffffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(6, 7);
                  break;
               case 0:
                  if ((0x7fffffffffffffffL & l) != 0L)
                  {
                     if (kind > 58)
                        kind = 58;
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 42)
                        kind = 42;
                     jjCheckNAdd(11);
                  }
                  else if ((0x78000001f8000001L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                  }
                  break;
               case 8:
                  if ((0x7fffffffffffffffL & l) != 0L && kind > 58)
                     kind = 58;
                  break;
               case 9:
                  if ((0x78000001f8000001L & l) != 0L && kind > 59)
                     kind = 59;
                  break;
               case 10:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(11);
                  break;
               case 11:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(11);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 28 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   13, 15, 24, 27, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, "\52", null, null, null, null, null, null, null, null, null, null, null, null, 
"\73", null, "\50", "\51", "\54", "\137", null, null, "\56", null, null, null, null, 
null, null, "\47", null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfffffffffffffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[28];
static private final int[] jjstateSet = new int[56];
static protected char curChar;
/** Constructor. */
public sqlparserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public sqlparserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 28; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 13 && (0x2600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
