package com.xiaosu.interviewapp.ui.activity.category.speciality;

import android.content.res.Resources;

import com.xiaosu.interviewapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Time: 2020/6/12
 * Author: Xiaosu
 * Description:分类》专业领域
 */
public class CategoryZSubject {
    private Resources mResources;

    private int arrayId;//门类资源

    private List<String> groupList = new ArrayList<>();//学科

    private List<List<String>> childList = new ArrayList<>();//专业


    public CategoryZSubject(Resources resources, int arrayId) {
        mResources = resources;
        this.arrayId = arrayId;
        setList();
    }

    private void setList() {
        String[] subject = mResources.getStringArray(arrayId);
        ;
        String[][] major = null;
        switch (arrayId) {
            case R.array.x_philosophy://哲学学科下的专业
                major = new String[][]{//专业
                        mResources.getStringArray(R.array.XPhilosophy)
                };
                break;
            case R.array.x_economics://经济学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XTheoreticalEconomics),
                        mResources.getStringArray(R.array.XAppliedEconomics),
                        mResources.getStringArray(R.array.XStatistics)
                };
                break;
            case R.array.x_law://法学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XLaw),
                        mResources.getStringArray(R.array.XPoliticalScience),
                        mResources.getStringArray(R.array.XSociology),
                        mResources.getStringArray(R.array.XEthnology),
                        mResources.getStringArray(R.array.XMarxistTheory),
                        mResources.getStringArray(R.array.XPublicSecurity)
                };
                break;
            case R.array.x_education://教育学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XEducation),
                        mResources.getStringArray(R.array.XPsychology),
                        mResources.getStringArray(R.array.XPhysicalEducation)
                };
                break;
            case R.array.x_history://历史学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XArchaeology),
                        mResources.getStringArray(R.array.XChineseHistory),
                        mResources.getStringArray(R.array.XWorldHistory)
                };
                break;
            case R.array.x_literature://文学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XChineseLanguageAndLiterature),
                        mResources.getStringArray(R.array.XForeignLanguageAndLiterature),
                        mResources.getStringArray(R.array.XJournalismAndCommunication)
                };
                break;
            case R.array.x_lixue://理学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XMath),
                        mResources.getStringArray(R.array.XPhysics),
                        mResources.getStringArray(R.array.XChemistry),
                        mResources.getStringArray(R.array.XAstronomy),
                        mResources.getStringArray(R.array.XGeography),
                        mResources.getStringArray(R.array.XAtmosphericScience),
                        mResources.getStringArray(R.array.XMarineScience),
                        mResources.getStringArray(R.array.XGeophysics),
                        mResources.getStringArray(R.array.XGeology),
                        mResources.getStringArray(R.array.XBiology),
                        mResources.getStringArray(R.array.XSystemScience),
                        mResources.getStringArray(R.array.XHistoryScienceTechnology),
                        mResources.getStringArray(R.array.XEcology),
                        mResources.getStringArray(R.array.XStatisticsMajor),
                        mResources.getStringArray(R.array.XPsychologyMajor),
                        mResources.getStringArray(R.array.XDynamics),
                        mResources.getStringArray(R.array.XMaterialsScienceEngineering),
                        mResources.getStringArray(R.array.XElectronicScienceTechnology),
                        mResources.getStringArray(R.array.XComputerScienceTechnology),
                        mResources.getStringArray(R.array.XEnvironmentalScienceEngineering),
                        mResources.getStringArray(R.array.XBiomedicalEngineering),
                        mResources.getStringArray(R.array.XBasicMedicine),
                        mResources.getStringArray(R.array.XPublicHealthPreventiveMedicine),
                        mResources.getStringArray(R.array.XPharmacy),
                        mResources.getStringArray(R.array.XChineseMedicine),
                        mResources.getStringArray(R.array.XMedicalTechnology),
                        mResources.getStringArray(R.array.XNursing)
                };
                break;
            case R.array.x_engineering://工学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XDynamics_G),
                        mResources.getStringArray(R.array.XMechanicalEngineering),
                        mResources.getStringArray(R.array.XOpticalEngineering),
                        mResources.getStringArray(R.array.XInstrumentScienceTechnology),
                        mResources.getStringArray(R.array.XMaterialsScienceEngineering_G),
                        mResources.getStringArray(R.array.XMetallurgicalEngineering),
                        mResources.getStringArray(R.array.XPowerEngineeringThermoPhysics),
                        mResources.getStringArray(R.array.XElectricalEngineering),
                        mResources.getStringArray(R.array.XElectronicScienceTechnology_G),
                        mResources.getStringArray(R.array.XInformationCommunicationEngineering),
                        mResources.getStringArray(R.array.XControlScienceEngineering),
                        mResources.getStringArray(R.array.XComputerScienceTechnology_G),
                        mResources.getStringArray(R.array.XArchitecture),
                        mResources.getStringArray(R.array.XCivilEngineering),
                        mResources.getStringArray(R.array.XWaterConservancyProject),
                        mResources.getStringArray(R.array.XSurveyingMapping),
                        mResources.getStringArray(R.array.XChemicalEngineeringTechnology),
                        mResources.getStringArray(R.array.XGeologicalResourcesGeologicalEngineering),
                        mResources.getStringArray(R.array.XMineralEngineering),
                        mResources.getStringArray(R.array.XOilGasEngineering),
                        mResources.getStringArray(R.array.XTextileScienceEngineering),
                        mResources.getStringArray(R.array.XLightIndustryTechnologyEngineering),
                        mResources.getStringArray(R.array.XTransportationEngineering),
                        mResources.getStringArray(R.array.XShipsMarineEngineering),
                        mResources.getStringArray(R.array.XAerospaceScienceTechnology),
                        mResources.getStringArray(R.array.XArmamentScienceTechnology_G),
                        mResources.getStringArray(R.array.XNuclearScienceTechnology),
                        mResources.getStringArray(R.array.XAgriculturalEngineering),
                        mResources.getStringArray(R.array.XForestryEngineering),
                        mResources.getStringArray(R.array.XEnvironmentalScienceEngineering_G),
                        mResources.getStringArray(R.array.XBiomedicalEngineering_G),
                        mResources.getStringArray(R.array.XFoodScienceEngineering),
                        mResources.getStringArray(R.array.XUrbanRuralPlanning),
                        mResources.getStringArray(R.array.XLandscapeArchitecture),
                        mResources.getStringArray(R.array.XSoftwareEngineering),
                        mResources.getStringArray(R.array.XBioengineering),
                        mResources.getStringArray(R.array.XSafetyScienceEngineering),
                        mResources.getStringArray(R.array.XPublicSecurityTechnology),
                        mResources.getStringArray(R.array.XCyberspaceSecurity),
                        mResources.getStringArray(R.array.XHistoryScienceTechnology_G),
                        mResources.getStringArray(R.array.XManagementScienceEngineering),
                        mResources.getStringArray(R.array.XDesignScience)
                };
                break;
            case R.array.x_agronomy://农学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XCropScience),
                        mResources.getStringArray(R.array.XHorticulture),
                        mResources.getStringArray(R.array.XAgriculturalResourcesEnvironment),
                        mResources.getStringArray(R.array.XPlantProtection),
                        mResources.getStringArray(R.array.XAnimalHusbandry),
                        mResources.getStringArray(R.array.XVeterinaryMedicine),
                        mResources.getStringArray(R.array.XForestry),
                        mResources.getStringArray(R.array.XAquaticProducts),
                        mResources.getStringArray(R.array.XHerbalism),
                        mResources.getStringArray(R.array.XHistoryScienceTechnology_N),
                        mResources.getStringArray(R.array.XEnvironmentalScienceEngineering_N),
                        mResources.getStringArray(R.array.XFoodScienceEngineering_N),
                        mResources.getStringArray(R.array.XLandscapeArchitecture_N)
                };
                break;
            case R.array.x_medical://医学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XBasicMedicine_Y),
                        mResources.getStringArray(R.array.XClinicalMedicine),
                        mResources.getStringArray(R.array.XStomatology),
                        mResources.getStringArray(R.array.XPublicHealthPreventiveMedicine_Y),
                        mResources.getStringArray(R.array.XTraditionalChineseMedicine),
                        mResources.getStringArray(R.array.XIntegrationTraditionalChinesEWesternMedicine),
                        mResources.getStringArray(R.array.XPharmacy_Y),
                        mResources.getStringArray(R.array.XChineseMedicine_Y),
                        mResources.getStringArray(R.array.XSpecialMedicine),
                        mResources.getStringArray(R.array.XMedicalTechnology_Y),
                        mResources.getStringArray(R.array.XNursing_Y),
                        mResources.getStringArray(R.array.XHistoryScienceTechnology_Y),
                        mResources.getStringArray(R.array.XBiomedicalEngineering_Y)
                };
                break;
            case R.array.x_management://管理学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XManagementScienceEngineering_G),
                        mResources.getStringArray(R.array.XBusinessAdministration),
                        mResources.getStringArray(R.array.XAgriculturalForestryEconomicManagement),
                        mResources.getStringArray(R.array.XPublicAdministration),
                        mResources.getStringArray(R.array.XLibraryInformationArchivesManagement)
                };
                break;
            case R.array.x_military://军事学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XMilitaryThoughtMilitaryHistory),
                        mResources.getStringArray(R.array.XScienceStrategy),
                        mResources.getStringArray(R.array.XScienceCampaign),
                        mResources.getStringArray(R.array.XScienceTactics),
                        mResources.getStringArray(R.array.XMilitaryCommand),
                        mResources.getStringArray(R.array.XMilitarySystem),
                        mResources.getStringArray(R.array.XMilitaryPoliticalWork),
                        mResources.getStringArray(R.array.XMilitaryLogistics),
                        mResources.getStringArray(R.array.XMilitaryEquipmentScience),
                        mResources.getStringArray(R.array.XMilitaryTraining)
                };
                break;
            case R.array.x_art://艺术学学科下的专业
                major = new String[][]{
                        mResources.getStringArray(R.array.XArtTheory),
                        mResources.getStringArray(R.array.XMusicDance),
                        mResources.getStringArray(R.array.XDramaFilmTelevision),
                        mResources.getStringArray(R.array.XFineArts),
                        mResources.getStringArray(R.array.XDesign)
                };
                break;
            default:
                break;
        }
        for (int i = 0; i < subject.length; i++) {
            groupList.add(subject[i]);
            List<String> item = new ArrayList<>();
            assert major != null;
            Collections.addAll(item, major[i]);
            childList.add(item);
        }
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public List<List<String>> getChildList() {
        return childList;
    }
}
