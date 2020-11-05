package lk.lab_management.asset.sample_receiving.service;

import lk.lab_management.asset.sample_receiving.dao.SampleReceivingLabTestResultDao;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTestResult;
import org.springframework.stereotype.Service;

@Service
public class SampleReceivingLabTestResultService {
    private final SampleReceivingLabTestResultDao sampleReceivingLabTestResultDao;

    public SampleReceivingLabTestResultService(SampleReceivingLabTestResultDao sampleReceivingLabTestResultDao) {
        this.sampleReceivingLabTestResultDao = sampleReceivingLabTestResultDao;
    }

    public SampleReceivingLabTestResult findById(Integer id) {
        return sampleReceivingLabTestResultDao.getOne(id);
    }

    public SampleReceivingLabTestResult persist(SampleReceivingLabTestResult sampleReceivingLabTestResult) {
        return null;
    }

}
