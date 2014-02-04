package org.openmrs.module.appointmentschedulingui.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.module.emrapi.patient.PatientDomainWrapper;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

public class PatientPageController {

    private static final Log log = LogFactory.getLog(PatientPageController.class);

    public void controller(@RequestParam("patientId") Patient patient,
                           UiUtils ui,
                           PageModel model,
                           @InjectBeans PatientDomainWrapper patientDomainWrapper) {

        patientDomainWrapper.setPatient(patient);
        SimpleObject appHomepageBreadcrumb = SimpleObject.create("label", ui.message("appointmentschedulingui.scheduleAppointment.title"),
                "link", ui.pageLink("dispensing", "findPatient?app=dispensing.app"));
        SimpleObject patientPageBreadcrumb = SimpleObject.create("label",
                patient.getFamilyName() + ", " + patient.getGivenName(), "link", ui.thisUrlWithContextPath());

        model.addAttribute("patient", patientDomainWrapper);
        model.addAttribute("breadcrumbOverride", ui.toJson(Arrays.asList(appHomepageBreadcrumb, patientPageBreadcrumb)));

    }
}
