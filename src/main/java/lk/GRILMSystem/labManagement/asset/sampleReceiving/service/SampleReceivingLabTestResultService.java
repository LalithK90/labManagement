package lk.GRILMSystem.labManagement.asset.sampleReceiving.service;

import lk.GRILMSystem.labManagement.asset.sampleReceiving.dao.SampleReceivingLabTestResultDao;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTestResult;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

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
