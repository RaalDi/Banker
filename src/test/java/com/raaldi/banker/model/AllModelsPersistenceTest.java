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
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(Arquillian.class)
@Transactional(manager = "transactionManager")
@SpringWebConfiguration(servletName = "dispatcher")
public class AllModelsPersistenceTest {

    private static final String[] COMPANY_NAMES = { "Rivera Brother" }; // ,
                                                                        // "Amazon",
                                                                        // "IBM"
                                                                        // };
    private static final String[] COMPANY_SHOPS = { "BANCA 1", "BANCA 2", "BANCA 3" };
    private static String[] TEST_COMPANY_SHOPS = { "", "", "" };
    private static final String[] STREET_NAMES = { "15350 Amberly Dr. Unit 1014" };// ,
                                                                                   // "15350
                                                                                   // Amberly
                                                                                   // Dr.
                                                                                   // Unit
                                                                                   // 1015",
                                                                                   // "15350
                                                                                   // Amberly
                                                                                   // Dr.
                                                                                   // Unit
                                                                                   // 1016"
                                                                                   // };
    private static final String[] USER_FIRST_NAMES = { "Rafael", "Alexander", "Candy" };
    private static final String[] USER_LAST_NAMES = { "Rivera", "Diaz", "Martinez" };
    private static final String[] USER_NAMES = { "riveralo", "diazotor", "martinezito" };
    private static String[] TEST_USER_NAMES = { "", "", "" };
    private static final String[] USER_PHONE_NUMBERS = { "8092886232", "3058096232", "7275155317" };
    private static final String[] USER_PASSWORDS = { "12345Qwerty", "Alexander09876",
            "asdfghj123567" };
    private static final String[] USER_ROLES = { "Administrator", "Manager", "User" };
    private static final String[] USER_PERMISSIONS = { "Edit", "View", "None" };
    private static final String[] LOTTERIES = { "Quiniella Pale", "Nacional", "Loteca", "Leisa" };
    private static final String[] PLAYS = { "Quiniella", "Pale", "Super Pale", "Tripleta" };
    private static final boolean[] ACTIVES = { true, false, true };
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

    @PersistenceContext
    EntityManager em;

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
         * "applicationContext.xml")
         * .addAsWebInfResource("test-banker-context.xml",
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
        // em.joinTransaction();
        System.out.println("###---> Dumping old records...");
        em.createQuery("delete from RolePermission").executeUpdate();
        em.createNativeQuery("delete from play_order_line_lottery").executeUpdate();
        em.createNativeQuery("delete from play_order_line_number").executeUpdate();
        em.createQuery("delete from PlayOrderLine").executeUpdate();
        em.createQuery("delete from PlayOrder").executeUpdate();
        em.createQuery("delete from CashRegister").executeUpdate();
        em.createQuery("delete from Session").executeUpdate();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Role").executeUpdate();
        em.createQuery("delete from Permission").executeUpdate();
        em.createQuery("delete from Shop").executeUpdate();
        em.createQuery("delete from Company").executeUpdate();
        em.createQuery("delete from Address").executeUpdate();
        em.createQuery("delete from Lottery").executeUpdate();
        em.createQuery("delete from Play").executeUpdate();
        em.createQuery("delete from Payment").executeUpdate();

        // utx.commit();
    }

    private void createCompany() throws Exception {
        // utx.begin();
        // em.joinTransaction();
        System.out.println("###---> Inserting records...");
        Company company = null;
        // List<User> users = null;
        List<Shop> shops = null;
        Address address = null;
        int offset = 0;
        // User user = null;
        Shop shop = null;
        // String userName = null;
        String shopName = null;
        List<Role> roles = createRoles();
        List<Permission> permissions = createPermissions();
        for (String name : COMPANY_NAMES) {

            company = new Company();
            company.setName(name);
            company.setCreatedUid(USER_ID);

            address = new Address();
            address.setCreatedUid(USER_ID);
            address.setStreet(STREET_NAMES[offset++]);
            address.setCity("Tampa");
            address.setState("Florida");
            address.setZipcode("33647");
            company.setAddress(address);
            // companyService.save(company);

            shops = new ArrayList<Shop>();
            for (int index = 0; index < COMPANY_SHOPS.length; index++) {
                shop = new Shop();
                setUsers(company, roles, permissions, shop, address);
                shopName = COMPANY_SHOPS[index] + (int) (Math.random() * 1000);
                TEST_COMPANY_SHOPS[index] = shopName;
                shop.setName(shopName);
                shop.setCreatedUid(USER_ID);
                shop.setAddress(address);
                shop.setCompany(company);
                shop.setActive(ACTIVES[index]);
                shops.add(shop);
            }

            company.setShops(shops);
            // company.setUsers(users);

            companyService.save(company);
            // em.flush();
        }

        createLotteries();
        createPlays();
        createPlayOrders();
        // utx.commit();
        // clear the persistence context (first-level cache)
        // em.flush();
    }

    private void setUsers(Company company, List<Role> roles, List<Permission> permissions,
            Shop shop, Address address) {
        List<User> users = new ArrayList<User>();
        String userName = null;
        User user = null;
        RolePermission rolePermission = null;
        Set<RolePermission> rolePermissions = null;

        for (int index = 0; index < USER_FIRST_NAMES.length; index++) {
            user = new User();
            user.setShop(shop);
            user.setCompany(company);
            user.setAddress(address);
            user.setCreatedUid(USER_ID);
            user.setFirstName(USER_FIRST_NAMES[index]);
            user.setLastName(USER_LAST_NAMES[index]);
            userName = USER_NAMES[index] + (int) (Math.random() * 1000);
            TEST_USER_NAMES[index] = userName;
            user.setUserName(userName);
            user.setPhoneNumber(USER_PHONE_NUMBERS[index]);
            user.setPassword(USER_PASSWORDS[index]);
            user.setActive(ACTIVES[index]);

            rolePermissions = new HashSet<RolePermission>();
            rolePermission = new RolePermission();
            rolePermission.setCreatedUid(USER_ID);
            rolePermission.setUser(user);
            rolePermission.setRole(roles.get(index));
            rolePermission.setPermission(permissions.get(index));
            rolePermissions.add(rolePermission);
            user.setRolePermissions(rolePermissions);

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
            role = new Role();
            role.setName(name);
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
            permission = new Permission();
            permission.setName(name);
            permission.setCreatedUid(USER_ID);
            permissions.add(permission);
            permissionService.save(permission);
        }

        return permissions;
    }

    private void createLotteries() {
        Lottery lottery = null;
        for (String name : LOTTERIES) {
            lottery = new Lottery();
            lottery.setCreatedUid(USER_ID);
            lottery.setName(name);
            lottery.setShortName(name);
            lottery.setActive(true);
            lotteryService.save(lottery);
        }
    }

    private void createPlays() {
        Play play = null;
        for (String name : PLAYS) {
            play = new Play();
            play.setCreatedUid(USER_ID);
            play.setName(name);
            play.setShortName(name);
            play.setActive(true);
            playService.save(play);
        }
    }

    private void createPlayOrders() {
        try {
            Shop shop = em.createQuery(String.format("SELECT s FROM Shop s WHERE s.name = '%s'",
                    TEST_COMPANY_SHOPS[0]), Shop.class).getSingleResult();
            User user = em
                    .createQuery(String.format("SELECT u FROM User u WHERE u.firstName = '%s'",
                            USER_FIRST_NAMES[0]), User.class)
                    .getResultList().get(0);
            Play play = null;
            Lottery lottery = null;
            Session session = new Session();
            session.setCreatedUid(USER_ID);
            // session.setCashRegister(cashRegister);
            session.setUser(user);
            sessionService.save(session);
            session.setState(EnumSessionState.STARTED);
            session.setUpdatedUid(USER_ID);
            // session.setStarted(new Date());

            CashRegister cashRegister = new CashRegister();
            cashRegister.setCreatedUid(USER_ID);
            // cashRegister.setOpened(new Date());
            cashRegister.setSession(session);
            cashRegisterService.save(cashRegister);
            cashRegister.setUpdatedUid(USER_ID);
            cashRegister.setOpenedAmount(new BigDecimal(1000.00D));
            cashRegister.setState(CashRegisterState.OPENED);

            Payment payment = new Payment();
            payment.setActive(true);
            payment.setCreatedUid(USER_ID);
            payment.setType("CASH");
            paymentService.save(payment);

            PlayOrder playOrder = new PlayOrder();
            playOrder.setAmount(new BigDecimal(20.00D));
            playOrder.setCashRegister(cashRegister);
            playOrder.setPayment(payment);
            playOrder.setShop(shop);
            playOrder.setCreatedUid(USER_ID);

            PlayOrderLine playOrderLine = null;
            PlayOrderLineLottery playOrderLineLottery = null;
            PlayOrderLineNumber playOrderLineNumber = null;

            for (int index = 0; index < PLAYS.length; ++index) {

                playOrderLine = new PlayOrderLine();
                playOrderLine.setAmount(new BigDecimal(5.00D));
                playOrderLine.setCreatedUid(USER_ID);
                playOrderLine.setPlayOrder(playOrder);
                play = em.createQuery(
                        String.format("SELECT p FROM Play p WHERE p.name = '%s'", PLAYS[index]),
                        Play.class).getSingleResult();
                playOrderLine.setPlay(play);

                for (int i = 0; i < 2; i++) {
                    playOrderLineLottery = new PlayOrderLineLottery();
                    // playOrderLineLottery.setCreatedUid(USER_ID);
                    lottery = em.createQuery(String
                            .format("SELECT l FROM Lottery l WHERE l.name = '%s'", LOTTERIES[i]),
                            Lottery.class).getSingleResult();
                    playOrderLineLottery.setLottery(lottery);
                    // playOrderLineLottery.setPlayOrder(playOrder);
                    playOrderLine.getLotteries().add(playOrderLineLottery);
                }

                for (int i = 0; i < 2; i++) {
                    playOrderLineNumber = new PlayOrderLineNumber();
                    // playOrderLineNumber.setCreatedUid(USER_ID);
                    playOrderLineNumber.setNumber(String.valueOf(Math.random() * 100));
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

    private void startTransaction() throws Exception {
        // utx.begin();
        // em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        // utx.commit();
        // em.flush();
    }

    @Test
    public void shouldFindAllCompaniesUsingJpqlQuery() throws Exception {
        // given
        String fetchingAllCompaniesInJpql = "select c from Company c order by c.id";

        // when
        System.out.println("Selecting (using JPQL)...");
        List<Company> companies = em.createQuery(fetchingAllCompaniesInJpql, Company.class)
                .getResultList();

        // then
        System.out.println("Found " + companies.size() + " Companies (using JPQL):");
        assertContainsAllCompanies(companies);
    }

    private static void assertContainsAllCompanies(Collection<Company> retrievedCompanies) {

        Assert.assertEquals(COMPANY_NAMES.length, retrievedCompanies.size());

        final Set<String> retrievedCompanyNames = new HashSet<String>();

        for (Company company : retrievedCompanies) {
            System.out.println("* " + company);
            retrievedCompanyNames.add(company.getName());
        }

        Assert.assertTrue(retrievedCompanyNames.containsAll(Arrays.asList(COMPANY_NAMES)));
    }

    @Test
    public void shouldFindAllAddressUsingJpqlQuery() throws Exception {
        // given
        String fetchingAllCompaniesInJpql = "select c from Address c order by c.id";

        // when
        System.out.println("Selecting (using JPQL)...");
        List<Address> addresses = em.createQuery(fetchingAllCompaniesInJpql, Address.class)
                .getResultList();

        // then
        System.out.println("Found " + addresses.size() + " Addresses (using JPQL):");
        assertContainsAllAddresses(addresses);
    }

    private static void assertContainsAllAddresses(Collection<Address> retrievedAddresses) {

        Assert.assertEquals(COMPANY_NAMES.length, retrievedAddresses.size());

        final List<String> retrievedAddressStreets = new ArrayList<String>();

        for (Address address : retrievedAddresses) {
            System.out.println("* " + address);
            retrievedAddressStreets.add(address.getStreet());
        }

        Assert.assertTrue(retrievedAddressStreets.containsAll(Arrays.asList(STREET_NAMES)));
    }

    @Test
    public void shouldFindAllUsersUsingJpqlQuery() throws Exception {
        // given
        String fetchingAllUsersInJpql = "select c from User c order by c.id";

        // when
        System.out.println("Selecting (using JPQL)...");
        List<User> users = em.createQuery(fetchingAllUsersInJpql, User.class).getResultList();

        // then
        System.out.println("Found " + users.size() + " Users (using JPQL):");
        assertContainsAllUsers(users);
    }

    private static void assertContainsAllUsers(Collection<User> retrievedUsers) {

        Assert.assertEquals(COMPANY_NAMES.length * TEST_USER_NAMES.length * COMPANY_SHOPS.length,
                retrievedUsers.size());

        final List<String> retrievedUserNames = new ArrayList<String>();

        for (User user : retrievedUsers) {
            System.out.println("* " + user);
            retrievedUserNames.add(user.getUserName());
        }

        Assert.assertTrue(retrievedUserNames.containsAll(Arrays.asList(TEST_USER_NAMES)));
    }

    @Test
    public void shouldFindAllShopsUsingJpqlQuery() throws Exception {
        // given
        String fetchingAllShopsInJpql = "select c from Shop c order by c.id";

        // when
        System.out.println("Selecting (using JPQL)...");
        List<Shop> shops = em.createQuery(fetchingAllShopsInJpql, Shop.class).getResultList();

        // then
        System.out.println("Found " + shops.size() + " Shops (using JPQL):");
        assertContainsAllShops(shops);
    }

    private static void assertContainsAllShops(Collection<Shop> retrievedShops) {

        Assert.assertEquals(COMPANY_NAMES.length * COMPANY_SHOPS.length, retrievedShops.size());

        final List<String> retrievedShopNames = new ArrayList<String>();

        for (Shop shop : retrievedShops) {
            System.out.println("* " + shop);
            retrievedShopNames.add(shop.getName());
        }

        Assert.assertTrue(retrievedShopNames.containsAll(Arrays.asList(TEST_COMPANY_SHOPS)));
    }

}
