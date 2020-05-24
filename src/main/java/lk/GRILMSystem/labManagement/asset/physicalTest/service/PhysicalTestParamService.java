package lk.GRILMSystem.labManagement.asset.physicalTest.service;

import lk.GRILMSystem.labManagement.asset.physicalTest.dao.PhysicalTestParamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalTestParamService {

    private final PhysicalTestParamDao physicalTestParamDao;

    @Autowired
    public PhysicalTestParamService(PhysicalTestParamDao physicalTestParamDao){
        this.physicalTestParamDao = physicalTestParamDao;
    }
}
