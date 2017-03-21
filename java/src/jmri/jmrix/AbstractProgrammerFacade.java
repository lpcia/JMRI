package jmri.jmrix;

import java.beans.PropertyChangeListener;
import java.util.List;
import jmri.ProgListener;
import jmri.Programmer;
import jmri.ProgrammerException;
import jmri.ProgrammingMode;

/**
 * Common implementations of the Programmer interface for making Programmer
 * facade classes.
 *
 * @author	Bob Jacobsen Copyright (C) 2013
 */
public abstract class AbstractProgrammerFacade implements Programmer {

    protected Programmer prog;

    public AbstractProgrammerFacade(Programmer prog) {
        this.prog = prog;
    }

    @Override
    public String decodeErrorCode(int code) {
        return prog.decodeErrorCode(code);
    }

    @Override
    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        prog.addPropertyChangeListener(l);
    }

    @Override
    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        prog.removePropertyChangeListener(l);
    }

    /**
     * Are facades allowed to cache programming operations?
     * E.g. skip writing a PI or SI CV if the correct value 
     * has already been written.<p>
     * Unbound parameter.<p>
     * This is common across all facades to simplify user
     * decision making. Someday, finer granularity might be desired.
     */
    static boolean canCacheDefault = false;
    
    static public boolean isCanCacheDefault() { return canCacheDefault; }
    static public void setCanCacheDefault(boolean v) { canCacheDefault = v; }
    
    /**
     * Should facades check index values after writing them?
     * E.g. read back a PI or SI value to confirm it has the
     * right value, and error out if not<p>
     * Unbound parameter.<p>
     * This is common across all facades to simplify user
     * decision making. Someday, finer granularity might be desired.
     */
    static boolean doConfirmRead = false;
    
    static public boolean isDoConfirmRead() { return doConfirmRead; }
    static public void setDoConfirmRead(boolean v) { doConfirmRead = v; }
    
    /**
     * @param CV  the CV to write
     * @param val the value to write
     * @param p   the listener that will be notified of the write
     * @throws jmri.ProgrammerException if unable to communicate
     * @deprecated As of 4.1.1, use #writeCV(java.lang.String, int,
     * jmri.ProgListener)
     * @see jmri.Programmer#writeCV(int, int, jmri.ProgListener) 
     */
    @Deprecated
    @Override
    public void writeCV(int CV, int val, ProgListener p) throws ProgrammerException {
        prog.writeCV(CV, val, p);
    }

    @Override
    public void writeCV(String CV, int val, ProgListener p) throws ProgrammerException {
        prog.writeCV(CV, val, p);
    }

    /**
     * @param CV the CV to read
     * @param p  the listener that will be notified of the read
     * @throws jmri.ProgrammerException if unable to communicate
     * @deprecated As of 4.1.1, use #readCV(java.lang.String, int,
     * jmri.ProgListener)
     * @see jmri.Programmer#readCV(int, jmri.ProgListener)
     */
    @Deprecated
    @Override
    public void readCV(int CV, ProgListener p) throws ProgrammerException {
        prog.readCV(CV, p);
    }

    @Override
    public void readCV(String CV, ProgListener p) throws ProgrammerException {
        prog.readCV(CV, p);
    }

    /**
     *
     * @param CV  the CV to confirm
     * @param val the value to confirm
     * @param p   the listener that will be notified of the confirmation
     * @throws jmri.ProgrammerException if unable to communicate
     * @see jmri.Programmer#confirmCV(int, int, jmri.ProgListener)
     * @deprecated since 4.1.1; use #confirmCV(java.lang.String, int,
     * jmri.ProgListener) instead.
     */
    @Override
    @Deprecated
    public final void confirmCV(int CV, int val, ProgListener p) throws ProgrammerException {
        prog.confirmCV(CV, val, p);
    }

    @Override
    public void confirmCV(String CV, int val, ProgListener p) throws ProgrammerException {
        prog.confirmCV(CV, val, p);
    }

    @Override
    public ProgrammingMode getMode() {
        return prog.getMode();
    }

    @Override
    public List<ProgrammingMode> getSupportedModes() {
        return prog.getSupportedModes();
    }

    @Override
    public void setMode(ProgrammingMode p) {
        prog.setMode(p);
    }

    @Override
    public boolean getCanRead() {
        return prog.getCanRead();
    }

    @Override
    public boolean getCanRead(String addr) {
        return prog.getCanRead(addr);
    }

    @Override
    public boolean getCanWrite() {
        return prog.getCanWrite();
    }

    @Override
    public boolean getCanWrite(String addr) {
        return prog.getCanWrite(addr);
    }

}
