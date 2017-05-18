/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import engines.views.PlayField;
import static org.junit.Assert.*;

/**
 *
 * @author Viktoria
 */
public class CollisionTest {
    
    public CollisionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //    public boolean checkCollistion(int x1, int y1, int size1, int x2, int y2, int size2) 

    //одинаковые спрайты
    @Test
    public void TestEqual() 
    {
        PlayField pf_test = new PlayField();
        assertFalse(pf_test.checkCollistion(1,1,1,1,1,1));
    }
    
    //На одной оси Y
    @Test
    public void TestEqualY() 
    {
        PlayField pf_test = new PlayField();
        assertFalse(pf_test.checkCollistion(3,1,2,4,1,2));
    }
    
    //public boolean checkCollistion(int x1, int y1, int size1, int x2, int y2, int size2) 
    //На одной оси X
    @Test
    public void TestEqualX() 
    {
        PlayField pf_test = new PlayField();
        assertFalse(pf_test.checkCollistion(1,4,5,1,7,9));
    }
    
    //На одной диагонали
    @Test
    public void TestDiagn() 
    {
        PlayField pf_test = new PlayField();
        assertFalse(pf_test.checkCollistion(1,1,1,3,3,4));
    }
    
    //На с одим центом и разными радиусами
    @Test
    public void TestEqualCenter() 
    {
        PlayField pf_test = new PlayField();
        assertFalse(pf_test.checkCollistion(1,1,1,1,1,5));
    }
    
}
