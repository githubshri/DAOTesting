/**
 *
 */
package test.daoLayer;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shrid
 *
 */
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionDAOTest {

	@Autowired
	private TransactionDAO transactionDAO;

	@Autowired
	private GuIdEntity guidEntity;

	@Test
	@Transactional
	@Rollback(true)
	public void testGetByGuId() {

		List transactionsByGuId = transactionDAO.getByGuId(guidEntity.getGuids());

		Assert.assertNotNull("list of Guid must not empty", transactionsByGuId);
		Assert.assertSame(transactionsByGuId, Matchers.not(Matchers.emptyCollectionOf(List.class)));
		Assert.assertThat(transactionsByGuId.toArray(), Matchers.contains(Matchers.typeCompatibleWith(String.class)));

	}

}
