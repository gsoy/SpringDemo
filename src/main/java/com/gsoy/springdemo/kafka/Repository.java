package com.gsoy.springdemo.kafka;

import java.security.SecureRandom;
import java.util.UUID;

import com.gsoy.springdemo.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


@Slf4j
@org.springframework.stereotype.Repository
public class Repository {

    private SimpleJdbcCall simpleJdbcCallForSave;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSourceManager")
    private PlatformTransactionManager platformTransactionManager;

    private static String INSERT_HHS_LOG = "INSERT INTO ab.hhs_log (HHSLOGNO,LOG_TAR,SERVIS_ADI,YOS_KOD,HHS_KOD,GROUP_ID,REQUEST_ID,PSU_INITIATED,HTTP_KOD,REQUEST,RESPONSE,HATA_ACK,TAR,SICILNO) " +
            "VALUES(?, TRUNC(SYSDATE), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

    public void insertHhsLog(Log log) {
        try {
            TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            jdbcTemplate.update(INSERT_HHS_LOG
                    , log.getLogNo()
                    , "YOS-" + log.getServisAdi()
                    , log.getYosKod()
                    , log.getHhsKod()
                    , log.getGroupId()
                    , log.getRequestId()
                    , log.getPsuInitiated()
                    , log.getHttpKod()
                    , log.getRequest()
                    , log.getResponse()
                    , log.getHataAck()
                    , log.getTar()
                    , log.getSicilno());
            platformTransactionManager.commit(status);
        } catch (Exception e) {
            throw e;
        }

    }

    public static String getUUID() {
        SecureRandom r = new SecureRandom();
        UUID u = new UUID(r.nextLong(), r.nextLong());
        return u.toString().replace("-", "");

    }

}
