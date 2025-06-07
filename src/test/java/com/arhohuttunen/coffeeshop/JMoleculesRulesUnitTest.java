package com.arhohuttunen.coffeeshop;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import org.jmolecules.archunit.JMoleculesArchitectureRules;

import static org.assertj.core.api.Assertions.assertThat;

@AnalyzeClasses(packages = "com.arhohuttunen.coffeeshop") // (1)
public class JMoleculesRulesUnitTest {
    @ArchTest // (3)
    void detectsViolations(JavaClasses classes) {
        ArchRule hexagonalRule = JMoleculesArchitectureRules.ensureHexagonal();

        EvaluationResult result = hexagonalRule.evaluate(classes);

        assertThat(result.hasViolation())
                .isFalse();
    }
}
