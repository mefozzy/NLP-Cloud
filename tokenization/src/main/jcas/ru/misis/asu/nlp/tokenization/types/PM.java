

/* First created by JCasGen Tue Mar 26 13:55:53 SAMT 2013 */
package ru.misis.asu.nlp.tokenization.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 26 13:55:53 SAMT 2013
 * XML source: /home/fsqcds/idea-projects/UIMA-Ext/UIMA.Ext.Tokenizer/src/main/resources/ru/kfu/cll/uima/tokenizer/jflex-tokenizer-ts.xml
 * @generated */
public class PM extends Token {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PM.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PM() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PM(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PM(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PM(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
}

    