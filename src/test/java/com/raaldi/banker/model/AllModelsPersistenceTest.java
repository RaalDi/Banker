package com.raaldi.banker.model;

import com.raaldi.banker.service.CashRegisterService;
import com.raaldi.banker.service.CompanyService;
import com.raaldi.banker.service.LotteryService;
import com.raaldi.banker.service.PaymentService;
import com.raaldi.banker.service.PermissionService;
import com.raaldi.banker.service.PlayOrderService;
import com.raaldi.banker.service.PlayService;
import com.raaldi.banker.service.RoleService;
import com.raaldi.banker.service.SessionService;
import com.raaldi.banker.util.CashRegisterState;
import com.raaldi.banker.util.EnumSessionState;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringWebConfiguration;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(Arquillian.class)
@Transactional(manager = "transactionManager")
@SpringWebConfiguration(servletName = "dispatcher")
@Slf4j
/** Address service provides access to the address repository. */
@NoArgsConstructor
public final class AllModelsPersistenceTest {

  private static final String[] COMPANY_NAMES = {"Rivera Brother"};
  private static final String[] COMPANY_SHOPS = {"BANCA 1", "BANCA 2", "BANCA 3"};
  private static String[] testCompanyShops = {"", "", ""};
  private static final String[] STREET_NAMES = {"15350 Amberly Dr. Unit 1014"};
  private static final String[] USER_FIRST_NAMES = {"Rafael", "Alexander", "Candy"};
  private static final String[] USER_LAST_NAMES = {"Rivera", "Diaz", "Martinez"};
  private static final String[] USER_NAMES = {"riveralo", "diazotor", "martinezito"};
  private static String[] testUserNames = {"", "", ""};
  private static final String[] USER_PHONE_NUMBERS = {"8092886232", "3058096232", "7275155317"};
  private static final String[] USER_PASSWORDS = {"12345Qwerty", "Alexander09876", "asdfghj123567"};
  private static final String[] USER_ROLES = {"Administrator", "Manager", "User"};
  private static final String[] USER_PERMISSIONS = {"Edit", "View", "None"};
  private static final String[] LOTTERIES = {"Quiniella Pale", "Nacional", "Loteca", "Leisa"};
  private static final String[] PLAYS = {"Quiniella", "Pale", "Super Pale", "Tripleta"};
  private static final boolean[] ACTIVES = {true, false, true};
  private static final Long USER_ID = 1L;
  @Autowired
  private CompanyService companyService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PermissionService permissionService;

  @Autowired
  private LotteryService lotteryService;

  @Autowired
  private PlayService playService;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private CashRegisterService cashRegisterService;

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private PlayOrderService playOrderService;

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  EntityManager entityManager;

  // @Resource
  // UserTransaction utx;
  // banker-context.xml
  @Deployment
  // @OverProtocol("Servlet 3.1")
  public static Archive<WebArchive> createDeployment() {

    /*
     * File[] files = Maven.resolver().resolve(
     * "org.springframework:spring-webmvc:4.2.4.RELEASE").withTransitivity()
     * .asFile();
     */
    WebArchive wrap = null;

    try {
      wrap = ShrinkWrap.create(EmbeddedGradleImporter.class, "banker-test.war")
          .forThisProjectDirectory().importBuildOutput().as(WebArchive.class)
          .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
          .addAsManifestResource("test-context.xml", "context.xml")
          // Arquillia does not support a single Spring context
          // configured in the web.xml
          .addAsWebInfResource("test-web.xml", "web.xml")
          .addAsWebInfResource("test-applicationContext.xml", "applicationContext.xml")
          //
          .addAsWebInfResource("test-banker-context.xml", "dispatcher-servlet.xml");
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return wrap;

    /*
     * return ShrinkWrap.create(WebArchive.class, "banker-test.war") //
     * .addClasses(Company.class, Model.class) //
     * .addAsLibraries(springDependencies()) //
     * .addAsLibraries(mockitoDependencies()) //.addAsLibraries()
     * .addPackage(CompanyDAO.class.getPackage())
     * .addPackage(CompanyService.class.getPackage())
     * .addPackage(Company.class.getPackage())
     * .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
     * .addAsManifestResource("test-context.xml", "context.xml")
     * .addAsWebInfResource("test-web.xml", "web.xml")
     * .addAsWebInfResource("test-applicationContext.xml",
     * "applicationContext.xml") .addAsWebInfResource("test-banker-context.xml",
     * "banker-servlet.xml");
     */

    /*
     * .addPackage(Company.class.getPackage())
     * .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
     * .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
     */

  }

  @Before
  public void preparePersistenceTest() throws Exception {
    clearCompany();
    createCompany();
    // startTransaction();
  }

  private void clearCompany() throws Exception {
    // utx.begin();
    // entityManager.joinTransaction();
    log.info("###---> Dumping old records...");
    entityManager.createQuery("delete from RolePermission").executeUpdate();
    entityManager.createNativeQuery("delete from play_order_line_lottery").executeUpdate();
    entityManager.createNativeQuery("delete from play_order_line_number").executeUpdate();
    entityManager.createQuery("delete from PlayOrderLine").executeUpdate();
    entityManager.createQuery("delete from PlayOrder").executeUpdate();
    entityManager.createQuery("delete from CashRegister").executeUpdate();
    entityManager.createQuery("delete from Session").executeUpdate();
    entityManager.createQuery("delete from User").executeUpdate();
    entityManager.createQuery("delete from Role").executeUpdate();
    entityManager.createQuery("delete from Permission").executeUpdate();
    entityManager.createQuery("delete from Shop").executeUpdate();
    entityManager.createQuery("delete from Company").executeUpdate();
    entityManager.createQuery("delete from Address").executeUpdate();
    entityManager.createQuery("delete from Lottery").executeUpdate();
    entityManager.createQuery("delete from Play").executeUpdate();
    entityManager.createQuery("delete from Payment").executeUpdate();

    // utx.commit();
  }

  private void createCompany() throws Exception {
    // utx.begin();
    // entityManager.joinTransaction();
    log.info("###---> Inserting records...");
    Company company;
    // List<User> users = null;
    List<Shop> shops;
    Address address;
    int offset = 0;
    // User user = null;
    Shop shop;
    // String userName = null;
    String shopName;
    final List<Role> roles = createRoles();
    final List<Permission> permissions = createPermissions();
    final Random random = new Random();
    for (String name : COMPANY_NAMES) {

      company = new Company(name);
      company.setCreatedUid(USER_ID);

      address = new Address(STREET_NAMES[offset++], "Tampa", "Florida", "33647");
      address.setCreatedUid(USER_ID);
      company.setAddress(address);
      // companyService.save(company);

      shops = new ArrayList<Shop>();
      for (int index = 0; index < COMPANY_SHOPS.length; index++) {
        shopName = COMPANY_SHOPS[index] + random.nextInt(1000);
        testCompanyShops[index] = shopName;
        shop = new Shop(shopName, company);
        setUsers(company, roles, permissions, shop, address);
        shop.setCreatedUid(USER_ID);
        shop.setAddress(address);
        shop.setActive(ACTIVES[index]);
        shops.add(shop);
      }

      company.setShops(shops);
      // company.setUsers(users);

      companyService.save(company);
      // entityManager.flush();
    }

    createLotteries();
    createPlays();
    createPlayOrders();
    // utx.commit();
    // clear the persistence context (first-level cache)
    // entityManager.flush();
  }

  private void setUsers(final Company company, final List<Role> roles,
      final List<Permission> permissions, final Shop shop, final Address address) {
    List<User> users = new ArrayList<User>();
    String userName = null;
    User user = null;
    RolePermission rolePermission = null;
    Set<RolePermission> rolePermissions = null;
    final Random ramdom = new Random();

    for (int index = 0; index < USER_FIRST_NAMES.length; index++) {
      userName = USER_NAMES[index] + ramdom.nextInt(1000);
      testUserNames[index] = userName;

      user = new User(USER_FIRST_NAMES[index], USER_LAST_NAMES[index], userName,
          USER_PHONE_NUMBERS[index], USER_PASSWORDS[index], company, shop);

      rolePermissions = new HashSet<RolePermission>();
      rolePermission = new RolePermission(user, roles.get(index), permissions.get(index));
      rolePermission.setCreatedUid(USER_ID);
      rolePermissions.add(rolePermission);

      user.setRolePermissions(rolePermissions);
      user.setAddress(address);
      user.setCreatedUid(USER_ID);
      user.setActive(ACTIVES[index]);
      users.add(user);
    }
    shop.setUsers(users);
    company.setUsers(users);
    // return users;
  }

  private List<Role> createRoles() {
    List<Role> roles = new ArrayList<Role>();
    Role role = null;
    for (String name : USER_ROLES) {
      role = new Role(name);
      role.setCreatedUid(USER_ID);
      roles.add(role);
      roleService.save(role);
    }
    return roles;
  }

  private List<Permission> createPermissions() {
    List<Permission> permissions = new ArrayList<Permission>();
    Permission permission = null;
    for (String name : USER_PERMISSIONS) {
      permission = new Permission(name);
      permission.setCreatedUid(USER_ID);
      permissions.add(permission);
      permissionService.save(permission);
    }

    return permissions;
  }

  private void createLotteries() {
    Lottery lottery = null;
    for (String name : LOTTERIES) {
      lottery = new Lottery(name, name);
      lottery.setCreatedUid(USER_ID);
      lottery.setActive(true);
      lotteryService.save(lottery);
    }
  }

  private void createPlays() {
    Play play = null;
    for (String name : PLAYS) {
      play = new Play(name, name);
      play.setCreatedUid(USER_ID);
      play.setActive(true);
      playService.save(play);
    }
  }

  private void createPlayOrders() {
    try {
      User user = entityManager.createQuery(
          String.format("SELECT u FROM User u WHERE u.firstName = '%s'", USER_FIRST_NAMES[0]),
          User.class).getResultList().get(0);
      Play play = null;
      Lottery lottery = null;
      Session session = new Session(user);
      session.setCreatedUid(USER_ID);
      // session.setCashRegister(cashRegister);
      sessionService.save(session);
      session.setState(EnumSessionState.STARTED);
      session.setUpdatedUid(USER_ID);
      // session.setStarted(new Date());

      CashRegister cashRegister = new CashRegister(session);
      cashRegister.setCreatedUid(USER_ID);
      // cashRegister.setOpened(new Date());
      cashRegisterService.save(cashRegister);
      cashRegister.setUpdatedUid(USER_ID);
      cashRegister.setOpenedAmount(new BigDecimal(1000.00D));
      cashRegister.setState(CashRegisterState.OPENED);

      Payment payment = new Payment("CASH");
      payment.setActive(true);
      payment.setCreatedUid(USER_ID);
      paymentService.save(payment);

      Shop shop = entityManager.createQuery(
          String.format("SELECT s FROM Shop s WHERE s.name = '%s'", testCompanyShops[0]),
          Shop.class).getSingleResult();

      PlayOrder playOrder = new PlayOrder(shop, new BigDecimal(20.00D), payment, cashRegister);
      playOrder.setCreatedUid(USER_ID);

      PlayOrderLine playOrderLine = null;
      PlayOrderLineLottery playOrderLineLottery = null;
      PlayOrderLineNumber playOrderLineNumber = null;

      for (String element : PLAYS) {

        play = entityManager
            .createQuery(String.format("SELECT p FROM Play p WHERE p.name = '%s'", element),
                Play.class)
            .getSingleResult();

        playOrderLine = new PlayOrderLine(playOrder, play, new BigDecimal(5.00D));
        playOrderLine.setCreatedUid(USER_ID);

        for (int i = 0; i < 2; i++) {

          lottery = entityManager.createQuery(
              String.format("SELECT l FROM Lottery l WHERE l.name = '%s'", LOTTERIES[i]),
              Lottery.class).getSingleResult();

          playOrderLineLottery = new PlayOrderLineLottery(lottery);
          // playOrderLineLottery.setCreatedUid(USER_ID);
          // playOrderLineLottery.setPlayOrder(playOrder);
          playOrderLine.getLotteries().add(playOrderLineLottery);
        }

        for (int i = 0; i < 2; i++) {
          playOrderLineNumber = new PlayOrderLineNumber(String.valueOf(Math.random() * 100));
          // playOrderLineNumber.setCreatedUid(USER_ID);
          // playOrderLineNumber.setPlayOrder(playOrder);
          playOrderLine.getNumbers().add(playOrderLineNumber);
        }

        playOrder.getPlayOrderLines().add(playOrderLine);
      }

      playOrderService.save(playOrder);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @After
  public void commitTransaction() throws Exception {
    // utx.commit();
    // entityManager.flush();
  }

  @Test
  public void shouldFindAllCompaniesUsingJpqlQuery() throws Exception {
    // given
    String fetchingAllCompaniesInJpql = "select c from Company c order by c.id";

    // when
    log.info("Selecting (using JPQL)...");
    List<Company> companies = entityManager.createQuery(fetchingAllCompaniesInJpql, Company.class)
        .getResultList();

    // then
    log.info(String.format("Found %d Companies (using JPQL):", companies.size()));
    assertContainsAllCompanies(companies);
  }

  private static void assertContainsAllCompanies(final Collection<Company> retrievedCompanies) {

    Assert.assertEquals(COMPANY_NAMES.length, retrievedCompanies.size());

    final Set<String> retrievedCompanyNames = new HashSet<String>();

    for (Company company : retrievedCompanies) {
      log.info(String.format("* %s", company));
      retrievedCompanyNames.add(company.getName());
    }

    Assert.assertTrue(retrievedCompanyNames.containsAll(Arrays.asList(COMPANY_NAMES)));
  }

  @Test
  public void shouldFindAllAddressUsingJpqlQuery() throws Exception {
    // given
    String fetchingAllCompaniesInJpql = "select c from Address c order by c.id";

    // when
    log.info("Selecting (using JPQL)...");
    List<Address> addresses = entityManager.createQuery(fetchingAllCompaniesInJpql, Address.class)
        .getResultList();

    // then
    log.info(String.format("Found %d Addresses (using JPQL):", addresses.size()));
    assertContainsAllAddresses(addresses);
  }

  private static void assertContainsAllAddresses(final Collection<Address> retrievedAddresses) {

    Assert.assertEquals(COMPANY_NAMES.length, retrievedAddresses.size());

    final List<String> retrievedAddressStreets = new ArrayList<String>();

    for (Address address : retrievedAddresses) {
      log.info(String.format("* %s", address));
      retrievedAddressStreets.add(address.getStreet());
    }

    Assert.assertTrue(retrievedAddressStreets.containsAll(Arrays.asList(STREET_NAMES)));
  }

  @Test
  public void shouldFindAllUsersUsingJpqlQuery() throws Exception {
    // given
    String fetchingAllUsersInJpql = "select c from User c order by c.id";

    // when
    log.info("Selecting (using JPQL)...");
    List<User> users = entityManager.createQuery(fetchingAllUsersInJpql, User.class)
        .getResultList();

    // then
    log.info(String.format("Found %d Users (using JPQL):", users.size()));
    assertContainsAllUsers(users);
  }

  private static void assertContainsAllUsers(final Collection<User> retrievedUsers) {

    Assert.assertEquals(COMPANY_NAMES.length * testUserNames.length * COMPANY_SHOPS.length,
        retrievedUsers.size());

    final List<String> retrievedUserNames = new ArrayList<String>();

    for (User user : retrievedUsers) {
      log.info(String.format("* %s", user));
      retrievedUserNames.add(user.getUserName());
    }

    Assert.assertTrue(retrievedUserNames.containsAll(Arrays.asList(testUserNames)));
  }

  @Test
  public void shouldFindAllShopsUsingJpqlQuery() throws Exception {
    // given
    String fetchingAllShopsInJpql = "select c from Shop c order by c.id";

    // when
    log.info("Selecting (using JPQL)...");
    final List<Shop> shops = entityManager.createQuery(fetchingAllShopsInJpql, Shop.class)
        .getResultList();

    // then
    log.info(String.format("Found %d Shops (using JPQL):", shops.size()));
    assertContainsAllShops(shops);
  }

  private static void assertContainsAllShops(final Collection<Shop> retrievedShops) {

    Assert.assertEquals(COMPANY_NAMES.length * COMPANY_SHOPS.length, retrievedShops.size());

    final List<String> retrievedShopNames = new ArrayList<String>();

    for (Shop shop : retrievedShops) {
      log.info(String.format("* %s", shop));
      retrievedShopNames.add(shop.getName());
    }

    Assert.assertTrue(retrievedShopNames.containsAll(Arrays.asList(testCompanyShops)));
  }

}
