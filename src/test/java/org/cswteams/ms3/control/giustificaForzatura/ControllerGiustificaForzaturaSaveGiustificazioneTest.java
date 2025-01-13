package org.cswteams.ms3.control.giustificaForzatura;

import org.cswteams.ms3.dto.GiustificazioneForzaturaVincoliDTO;
import org.cswteams.ms3.dto.medicalservice.MedicalServiceDTO;
import org.cswteams.ms3.entity.Schedule;
import org.cswteams.ms3.entity.User;
import org.cswteams.ms3.enums.SystemActor;
import org.cswteams.ms3.enums.TimeSlot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/***********************************************************************************
 * This class has the responsibility of testing the saveGiustificazione method of  *
 * class ControllerGiustificaForzatura. In particular the code is divided in 3     *
 * different parts:                                                                *
 * -  SETUP
 * -  DOMAIN PARTITION
 * -  TEST
 ***********************************************************************************/
@SpringBootTest
class ControllerGiustificaForzaturaSaveGiustificazioneTest extends ControllerGiustificaForzaturaTest{
    private static final Logger log = LoggerFactory.getLogger(ControllerGiustificaForzaturaSaveGiustificazioneTest.class);
    private static Schedule scheduleMock;
    @Autowired
    ControllerGiustificaForzatura controllerGiustificaForzatura;

    /**************************************************************
     *                          SETUP                             *
     *************************************************************/

    // SetUp and CleanUp of the testing environment
    @BeforeAll
    static void setUp() {
        scheduleMock = mock(Schedule.class);

        log.info("[DEBUG] [TEST] Set-up going on....");
    }

    @AfterAll
    static void tearDown() {
        /* pass */
    }


    /*************************************************************
     *                    DOMANI PARTITION                       *
     *************************************************************/


    private static GiustificazioneForzaturaVincoliDTO constructorGiustificazioneForzaturaVincoliDTOPartition(
            Set<User> setUtenti,
            int day,
            MedicalServiceDTO service,
            String justificationID,
            TimeSlot turnType
    ){
        GiustificazioneForzaturaVincoliDTO giustificazioneForzaturaVincoliDTO = new GiustificazioneForzaturaVincoliDTO();

        Set<Long> utentiAllocatiIDs = new HashSet<>();
        for(User user : setUtenti){
            utentiAllocatiIDs.add(user.getId());
        }
        

        //Populate justification
        giustificazioneForzaturaVincoliDTO.setUtentiAllocati(utentiAllocatiIDs);
        giustificazioneForzaturaVincoliDTO.setGiorno(day);
        giustificazioneForzaturaVincoliDTO.setServizio(service);
        giustificazioneForzaturaVincoliDTO.setUtenteGiustificatoreId(justificationID);
        giustificazioneForzaturaVincoliDTO.setTimeSlot(turnType);

        return giustificazioneForzaturaVincoliDTO;
    }

    /**************************************************************************************
     *                                                                                    *
     * DOMAIN PARTITIONING FOR giustificazioneForzaturaVincoliDTO ATTRIBUTE IN PARTITION1 *
     *                                                                                    *
     *************************************************************************************/
    private static GiustificazioneForzaturaVincoliDTO generateGiustifica(int caseNumber) {
        GiustificazioneForzaturaVincoliDTO giustificazioneForzaturaVincoliDTO;

        // Initialize the system having only one user
        Set<User> setUtenti = new HashSet<>();

        User user = new User(
                "Simone",
                "Staccone",
                "simone.staccone@virgilio.it",
                LocalDate.of(2020, 1,8),
                "SPECIALIST_SENIOR",
                "psw",
                Collections.singleton(SystemActor.DOCTOR)
        );

        User emptyUser = mock(User.class);
        when(emptyUser.getId()).thenReturn(null);
        when(emptyUser.getName()).thenReturn(null);
        when(emptyUser.getLastname()).thenReturn(null);
        when(emptyUser.getTaxCode()).thenReturn(null);
        when(emptyUser.getBirthday()).thenReturn(null);
        when(emptyUser.getEmail()).thenReturn(null);
        when(emptyUser.getPassword()).thenReturn(null);
        when(emptyUser.getSystemActors()).thenReturn(null);

        switch (caseNumber) {
            case 1:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1 ,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 2:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 3:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 4:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,3,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 5:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("radiologia"),"1", TimeSlot.NIGHT);
                break;
            case 6:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"2", TimeSlot.NIGHT);
                break;
            case 7:
                setUtenti.add(user);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 8:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 9:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 10:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 11:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,3,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 12:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("radiologia"),"1", TimeSlot.NIGHT);
                break;
            case 13:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"2", TimeSlot.NIGHT);
                break;
            case 14:
                setUtenti.add(emptyUser);
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(
                        setUtenti,
                        1,
                        new MedicalServiceDTO("cardiologia"),
                        "1",
                        TimeSlot.NIGHT);
                break;
            case 15:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 16:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 17:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 18:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,3,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            case 19:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("radiologia"),"1", TimeSlot.NIGHT);
                break;
            case 20:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"2", TimeSlot.NIGHT);
                break;
            case 21:
                setUtenti = null;
                giustificazioneForzaturaVincoliDTO = constructorGiustificazioneForzaturaVincoliDTOPartition(setUtenti,1,new MedicalServiceDTO("cardiologia"),"1", TimeSlot.NIGHT);
                break;
            default:
                throw new IllegalAccessError();
        }
        return giustificazioneForzaturaVincoliDTO;
    }





    /** *********************************************************************
    * DOMAIN PARTITIONING FOR PARTITION1
    * () -> (setUsers, year, day, month, service, justificationID, turnType)
    * (valid instance)
    *       -> users[1](1,Simone,Staccone,"2020-08-01","STCSMN0016D0O",SPECIALIZZANDO,"simone.staccone@virgilio.it","psw",categories, specializations,UTENTE),
    *           2020,08,01,"cardiologia","1",MATTUTINO)
    * (invalid instance)
    *       -> (
    *           users[1](1,Simone,Staccone,"2020-08-01","STCSMN0016D0O",SPECIALIZZANDO,"simone.staccone@virgilio.it","psw",categories, specializations,UTENTE)
    *           ,2023,08,01,"cardiologia","1",MATTUTINO
    *           )
    *       -> (
    *           users[1](1,Simone,Staccone,"2020-08-01","STCSMN0016D0O",SPECIALIZZANDO,"simone.staccone@virgilio.it","psw",categories, specializations,UTENTE)
    *           ,2020,09,01,"cardiologia","1",MATTUTINO
    *           )
    *       -> (
    *               ...
    *           )
    * (null instance)
    * The main difference in invalid instance is the difference between UtenteDTO and test case
    * configuration: this is a single class domain partitioning for all attributes except for
    * setUsers (good/wrong/null)
    * **********************************************************************/
    private static Stream<Arguments> partition() {
        return Stream.of(
                //setUsers full
                Arguments.of(generateGiustifica(1),false),
                Arguments.of(generateGiustifica(2),true),
                Arguments.of(generateGiustifica(3),true),
                Arguments.of(generateGiustifica(4),true),
                Arguments.of(generateGiustifica(5),true),
                Arguments.of(generateGiustifica(6),true),
                Arguments.of(generateGiustifica(7),true),


                //setUsers empty
                Arguments.of(generateGiustifica(8),true),
                Arguments.of(generateGiustifica(9),true),
                Arguments.of(generateGiustifica(10),true),
                Arguments.of(generateGiustifica(11),true),
                Arguments.of(generateGiustifica(12),true),
                Arguments.of(generateGiustifica(13),true),
                Arguments.of(generateGiustifica(14),true),

                //setUsers null
                Arguments.of(generateGiustifica(15),true),
                Arguments.of(generateGiustifica(16),true),
                Arguments.of(generateGiustifica(17),true),
                Arguments.of(generateGiustifica(18),true),
                Arguments.of(generateGiustifica(19),true),
                Arguments.of(generateGiustifica(20),true),
                Arguments.of(generateGiustifica(21),true),

                //null object
                Arguments.of(null,true)
        );
    }



    /**************************************************************
     *                           TEST                             *
     *************************************************************/


    // Actual test
    @ParameterizedTest
    @MethodSource("partition")
    @Override
    public void saveGiustificazione(GiustificazioneForzaturaVincoliDTO giustificazioneForzaturaVincoli, boolean expectedResult) {
        assertNotNull(controllerGiustificaForzatura); //Check autowiring worked
        if (!expectedResult) {
            assertDoesNotThrow(() -> controllerGiustificaForzatura.saveGiustificazione(giustificazioneForzaturaVincoli));
        } else {
            assertThrows(Exception.class, () -> controllerGiustificaForzatura.saveGiustificazione(giustificazioneForzaturaVincoli));
        }

    }

}