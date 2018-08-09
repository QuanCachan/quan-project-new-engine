/**
 *     This file is part of the Squashtest platform.
 *     Copyright (C) 2018 - 2018 HENIX
 *
 *     See the NOTICE file distributed with this work for additional
 *     information regarding copyright ownership.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     this software is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses />.
 */
package fr.henix.squash.framework.test.definition;

import fr.henix.squash.framework.test.instructions.ExecuteCommandInstruction;
import fr.henix.squash.framework.test.instructions.MetaInstruction;
import fr.henix.squash.framework.test.instructions.TestInstruction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * A Test is made of three phases : setup, test and teardown. Those three phases
 * exist for test semantic only and you may put any instruction you like in
 * either of them. The only special case is the teardown phase, that will always
 * be executed even when an exception occurs.<br>
 * A test is virtual when there is no existing script file associated to this
 * test.<br>
 * A test could have parameters provided through the test suite definition.
 *
 * @author qtran
 */
public class Test {

    private String name;

    private String testId;

    /**
     * A test is virtual if the associated ta script doesn't exist
     */
    private boolean virtualTest = false;

    /**
     * Parameter of the test given by the user through the test suite definition
     */
    private Properties scriptParams = new Properties();

    private List<TestInstruction> setupPhase = new LinkedList<TestInstruction>();
    private List<TestInstruction> testPhase = new LinkedList<TestInstruction>();
    private List<TestInstruction> teardownPhase = new LinkedList<TestInstruction>();

    // used to isolate the needed targets
    private List<ExecuteCommandInstruction> commandInstructions = new ArrayList<ExecuteCommandInstruction>();

    /* ******************************** getters *************************** */
    public String getName() {
        return name;
    }

    public String getTestId() {
        return testId;
    }

    public Iterator<TestInstruction> getSetup() {
        return setupPhase.iterator();
    }

    public Iterator<TestInstruction> getTests() {
        return testPhase.iterator();
    }

    public Iterator<TestInstruction> getTeardown() {
        return teardownPhase.iterator();
    }

    /* ******************************** adders **************************** */
    public void setName(String name) {
        this.name = name;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public void addToSetup(TestInstruction instr) {
        setupPhase.add(instr);
    }

    public void addToTests(TestInstruction instr) {
        testPhase.add(instr);
    }

    public void addToTeardown(TestInstruction instr) {
        teardownPhase.add(instr);
    }

    public void addToSetup(Iterator<TestInstruction> instructionSource) {
        while (instructionSource.hasNext()) {
            setupPhase.add(instructionSource.next());
        }
    }

    public void addToTests(Iterator<TestInstruction> instructionSource) {
        while (instructionSource.hasNext()) {
            testPhase.add(instructionSource.next());
        }
    }

    public void addToTeardown(Iterator<TestInstruction> instructionSource) {
        while (instructionSource.hasNext()) {
            teardownPhase.add(instructionSource.next());
        }
    }

    public void addToSetup(List<TestInstruction> instrs) {
        setupPhase.addAll(instrs);
    }

    public void addToTests(List<TestInstruction> instrs) {
        testPhase.addAll(instrs);
    }

    public void addToTeardown(List<TestInstruction> instrs) {
        teardownPhase.addAll(instrs);
    }

    /**
     * Get all the targets used inside the test, in order to instantiate them
     *
     * @return a set of the unique target names used in the test
     */
    public Set<String> getTargetsNames() {

        // first we fill commandsInstructions with all the command instructions in the test
        List<TestInstruction> instructions = new LinkedList<TestInstruction>();
        instructions.addAll(setupPhase);
        instructions.addAll(testPhase);
        instructions.addAll(teardownPhase);
        getCommandInstructions(instructions);

        // at last, we extract the targets of the command instructions
        Set<String> targetsNames = new HashSet<String>();
        for (ExecuteCommandInstruction instruction : commandInstructions) {
            targetsNames.add(instruction.getTargetName());
        }
        return targetsNames;
    }

    private void getCommandInstructions(List<TestInstruction> instructions) {

        List<TestInstruction> macrosInstructions = new ArrayList<TestInstruction>();

        for (TestInstruction instruction : instructions) {
            if (instruction.getType().isMeta()) {
                macrosInstructions.addAll(((MetaInstruction) instruction).getInstructions());
            } else if (instruction.getType().isCommand()) {
                commandInstructions.add((ExecuteCommandInstruction) instruction);
            }
        }

        // the we loop on the the meta instructions until only commands remain
        if (!macrosInstructions.isEmpty()) {
            getCommandInstructions(macrosInstructions);
        }
    }

    /**
     * Verify whether the current test is associated to an existing TA script
     * file
     *
     * @return <code>true</code> if there is no existing TA script file
     * associated to the current test
     */
    public boolean isVirtual() {
        return virtualTest;
    }

    /**
     * This method set this current test as a virtual. This means that there is
     * no existing TA script file associated to this test.
     */
    public void setAsVirtual() {
        this.virtualTest = true;
    }

    /**
     * Retrieve the parameters associated to the current test
     *
     * @return The test parameters
     */
    public Properties getScriptParams() {
        return scriptParams;
    }

    /**
     * Set the parameters associated to the current test
     *
     * @param scriptParam
     */
    public void setScriptParam(Properties scriptParam) {
        this.scriptParams = scriptParam;
    }
}
