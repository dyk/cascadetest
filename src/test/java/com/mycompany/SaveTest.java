package com.mycompany;

import static com.mycompany.DaoTestUtils.buildFactory;
import static junit.framework.Assert.fail;
import junit.framework.Assert;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

//mądry komentarz
public class SaveTest {

	@Test
	public void childShouldSaveParent() {
		
		try {
			SessionFactory factory = buildFactory(Parent.class, Child.class);
			Session session = factory.openSession();
			
			Child child = new Child();
			child.setName("test");
			
			
			Parent parent = new Parent();
			parent.setName("test");
			child.setParent(parent);
			
			session.save(child);
			
			session.flush();
			session.clear();
		} catch (HibernateException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
}
