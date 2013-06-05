

/* First created by JCasGen Sat Jun 01 21:28:54 MSK 2013 */
package ru.misis.asu.nlp.segmentation.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import ru.misis.asu.nlp.tokenization.types.PM;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jun 01 21:28:54 MSK 2013
 * XML source: C:/Users/Valter/new_uima/UIMA-Ext/segmentation/src/main/resources/uima.xml/segmentation-ts.xml
 * @generated */
public class PMSegment extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PMSegment.class);
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
  protected PMSegment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PMSegment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PMSegment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PMSegment(JCas jcas, int begin, int end) {
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
     
 
    
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets 
   * @generated */
  public Sentence getSentence() {
    if (PMSegment_Type.featOkTst && ((PMSegment_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PMSegment_Type)jcasType).casFeatCode_sentence)));}
    
  /** setter for sentence - sets  
   * @generated */
  public void setSentence(Sentence v) {
    if (PMSegment_Type.featOkTst && ((PMSegment_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    jcasType.ll_cas.ll_setRefValue(addr, ((PMSegment_Type)jcasType).casFeatCode_sentence, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: eopm

  /** getter for eopm - gets 
   * @generated */
  public PM getEopm() {
    if (PMSegment_Type.featOkTst && ((PMSegment_Type)jcasType).casFeat_eopm == null)
      jcasType.jcas.throwFeatMissing("eopm", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    return (PM)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PMSegment_Type)jcasType).casFeatCode_eopm)));}
    
  /** setter for eopm - sets  
   * @generated */
  public void setEopm(PM v) {
    if (PMSegment_Type.featOkTst && ((PMSegment_Type)jcasType).casFeat_eopm == null)
      jcasType.jcas.throwFeatMissing("eopm", "ru.misis.asu.nlp.segmentation.types.PMSegment");
    jcasType.ll_cas.ll_setRefValue(addr, ((PMSegment_Type)jcasType).casFeatCode_eopm, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    