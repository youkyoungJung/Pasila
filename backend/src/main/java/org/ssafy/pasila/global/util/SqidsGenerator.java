//package org.ssafy.pasila.global.util;
//
//import jakarta.persistence.SequenceGenerator;
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SessionImplementor;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.Configurable;
//import org.hibernate.id.IdentifierGenerator;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import org.hibernate.id.uuid.UuidGenerator;
//import org.sqids.Sqids;
//
//public class SqidsGenerator extends UuidGenerator {
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
//        Serializable id = session.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, session);
//
//        if (id != null) {
//            return id;
//        } else {
//            return generateSQID();
//        }
//    }
//
//    private Serializable generateSQID() {
//        Sqids sqids = Sqids.builder()
//                .minLength(10)
//                .build();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        long timestampAsLong = timestamp.getTime();
//        String newId = sqids.encode(List.of(timestampAsLong));
//
//        return newId;
//    }
//}
