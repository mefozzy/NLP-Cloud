

/* First created by JCasGen Sun Jun 09 00:35:52 MSK 2013 */
package ru.misis.asu.nlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Sun Jun 09 00:35:52 MSK 2013
 * XML source: D:/Java/workspace2/extended-grammatic-vector/src/main/resources/typeSystemDescriptor.xml
 * @generated */
public class InclinationParticle extends Service {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(InclinationParticle.class);
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
  protected InclinationParticle() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public InclinationParticle(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public InclinationParticle(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public InclinationParticle(JCas jcas, int begin, int end) {
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

    