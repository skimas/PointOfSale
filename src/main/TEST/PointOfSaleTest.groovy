import com.mwlazlowski.pointofsale.PointOfSale

class PointOfSaleTest extends GroovyTestCase {

    void testScanProductAndValidateOnLCD() {
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setupDatabase();

        assertEquals(true, pointOfSale.addProduct("123456780"))
        assertEquals(true, pointOfSale.addProduct("123456781"))
        //Product not found
        assertEquals(false, pointOfSale.addProduct("123456784"))
        //Invalid bar-code
        assertEquals(false, pointOfSale.addProduct(""))
        //exiting and printing receipt
        assertEquals(false, pointOfSale.addProduct("exit"))
    }
}
