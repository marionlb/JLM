package lessons.oop;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BugTest {
	Bug b1,b2,b3;
	int x,y,dir;
	Random r;

	@Before
	public void setUp() throws Exception {
		b1 = new Bug();
		b2 = new Bug(2, 3, 1);
		r = new Random();
		x=r.nextInt(Bug.tailleMonde);
		y=r.nextInt(Bug.tailleMonde);
		dir=r.nextInt(4);
		b3 = new Bug(x,y,dir);
	}

	
	//	/**
	//	 * Test method for {@link java.lang.Object#toString()}.
	//	 */
	//	@Test
	//	public final void testToString() {
	//		fail("Not yet implemented");
	//	}
		
		@Test
		public final void testHasConstructorVoid() {
			assertTrue( hasConstructor(new Class[] {}));
		}


	@Test
	public final void testHasConstructorIntIntInt() {
		assertTrue( hasConstructor(new Class[] {int.class,int.class,int.class}));
	}


	@Test
	public final void testHasConstructorIntInt() {
		assertTrue( hasConstructor(new Class[] {int.class,int.class}));
	}


	@Test
	public final void testFields() {
		Field[] fields = fields();
		assertTrue(fields.length>3);
		Field fx=null,fy=null,fd=null,fb=null;
		for (int i = 0; i < fields.length; i++) {
			if(fields[i].getName().toLowerCase().contains("x"))
				fx=fields[i];
			else if (fields[i].getName().toLowerCase().contains("y"))
				fy=fields[i];
			else if (fields[i].getName().toLowerCase().contains("dir"))
				fd=fields[i];
			else if (fields[i].getName().toLowerCase().contains("brush"))
				fb=fields[i];
		}
		assertNotNull(fx);
		assertEquals( int.class,fx.getType());
		assertNotNull(fy);
		assertEquals( int.class,fy.getType());
		assertNotNull(fd);
		assertEquals(int.class,fd.getType());
		assertNotNull(fb);
		assertEquals(boolean.class,fb.getType());
		
	}


	/**
	 * Test method for {@link lessons.oop.Bug#Bug(int, int, int)}.
	 */
	@Test
	public final void testBugIntInt() {
		b3 = new Bug(x,y);
		assertEquals(x, b3.getX());
		assertEquals(y, b3.getY());
	}

	/**
	 * Test method for {@link lessons.oop.Bug#Bug(int, int, int)}.
	 */
	@Test
	public final void testBugIntIntInt() {
		b3 = new Bug(x,y,dir);
		assertEquals(x, b3.getX());
		assertEquals(y, b3.getY());
		assertEquals(dir, b3.getDirection());
	}

	/**
	 * Test method for {@link lessons.oop.Bug#getX()}.
	 * Test simple sans %
	 */
	@Test
	public final void testPosX1() {
		x=r.nextInt(Bug.tailleMonde);
		b3.setX(x);
		assertEquals(x, b3.getX());
	}

	/**
	 * Test method for {@link lessons.oop.Bug#getY()}.
	 *  Test simple sans %
	 */
	@Test
	public final void testPosY1() {
		y=r.nextInt(Bug.tailleMonde);
		b3.setY(y);
		assertEquals(y, b3.getY());
	}
	/**
	 * Test method for {@link lessons.oop.Bug#getX()}.
	 *  Test vérifiant les limites du monde
	 */
	@Test
	public final void testPosX2() {
		assertTrue(b3.getX()>=0  && b3.getX()<Bug.tailleMonde );
		x=r.nextInt(100);
		b3.setX(x);
		assertEquals(x%Bug.tailleMonde, b3.getX());
	}

	/**
	 * Test method for {@link lessons.oop.Bug#getY()}.
	 *  Test vérifiant les limites du monde
	 */
	@Test
	public final void testPosY2() {
		assertTrue(b3.getY()>=0  && b3.getY()<Bug.tailleMonde );		
		y=r.nextInt(100);
		b3.setY(y);
		assertEquals(y%Bug.tailleMonde, b3.getY());
	}

	/**
	 * Test method for {@link lessons.oop.Bug#setBrushDown(boolean)}.
	 */
	@Test
	public final void testBrushDown() {
		b3.setBrushDown(false);
		assertFalse(b1.isBrushDown());
		b3.setBrushDown(true);
		assertTrue(b3.isBrushDown());
	}

	/**
	 * Test method for {@link lessons.oop.Bug#setDirection(int)}.
	 */
	@Test
	public final void testDirection() {
		assertTrue(b3.getDirection()>=0  && b3.getDirection()<4 );
		for (int i = 0; i < 4; i++) {
			b3.setDirection(i);
			assertEquals(i, b3.getDirection());
		}
	}
	
	private final boolean hasConstructor(Class<?>[] param) {
		Constructor<Bug> c =null;
		try {
			c = Bug.class.getDeclaredConstructor(param);
		} catch (NoSuchMethodException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}
		return c !=null;
	}


private final Field[] fields() {
	return Bug.class.getDeclaredFields();
}
	
}
