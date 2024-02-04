package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.fields.validations.annotations.CcpFieldValidationBoundsRules;
import com.ccp.fields.validations.annotations.CcpFieldValidationRestrictedValuesRules;
import com.ccp.fields.validations.annotations.CcpFieldValidationRules;
import com.ccp.fields.validations.annotations.CcpFieldValidationSimpleRules;
import com.ccp.fields.validations.enums.BoundsValidations;
import com.ccp.fields.validations.enums.RestrictedValuesValidations;
import com.ccp.fields.validations.enums.SimpleValidations;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}/steps/0")
public class VisResumeStep0DadosGeraisController {

	@PostMapping
	@CcpFieldValidationRules(simple = {
		@CcpFieldValidationSimpleRules(rule = SimpleValidations.RequiredFields, fields = {"onlyHomeOffice", "ddds", "pcd", "disabilities", "companiesNotAllowed", "disponibility", "observations"}),
		@CcpFieldValidationSimpleRules(rule = SimpleValidations.NonRepeatedLists, fields = {"ddds","disabilities", "companiesNotAllowed"}),
		@CcpFieldValidationSimpleRules(rule = SimpleValidations.ListFields, fields = {"ddds", "disabilities", "companiesNotAllowed"}),
		@CcpFieldValidationSimpleRules(rule = SimpleValidations.IntegerFields, fields = {"ddds","disponibility"}),
		@CcpFieldValidationSimpleRules(rule = SimpleValidations.BooleanFields, fields = {"onlyHomeOffice","pcd"}),
	},restrictedValues = {
			@CcpFieldValidationRestrictedValuesRules(rule = RestrictedValuesValidations.FieldArrayNumbersThatAreContainedAtTheList , fields =  {"ddd"}, allowedValues = {"61", "62", "64", "65", "66", "67", "82", "71", "73", "74", "75", "77", "85", "88", "98", "99", "83", "81", "87", "86", "89", "84", "79", "68", "96", "92", "97", "91", "93", "94", "69", "95", "63", "27", "28", "31", "32", "33", "34", "35", "37", "38", "21", "22", "24", "11", "12", "13", "14", "15", "16", "17", "18", "19", "41", "42", "43", "44", "45", "46", "51", "53", "54	", "55", "47", "48", "49"}),
			@CcpFieldValidationRestrictedValuesRules(rule=RestrictedValuesValidations.FieldArrayTextsThatAreContainedAtTheList ,fields ={"disabilities"}, allowedValues = {"física/motora", "intelectual/mental", "visual", "auditiva"})
	},bounds = {
			@CcpFieldValidationBoundsRules(rule = BoundsValidations.FieldObjectTextsWithSizeEqualsOrLessThan, fields = {"observations"}, bound = 500)
	}
)
	public Map<String, String> create(@RequestBody Map<String, Object> body){
		return null;
	}
	
}
