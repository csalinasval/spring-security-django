spring-security-django
======================

Spring Security extension for integrate java applications with Django 1.6 database users originally made for testing purposes for www.agronometrics.com


- Java 8 Needed
- For using it with spring configuration. Example:

```
 <bean id="passwordEncoder" class="com.agronometrics.security.authentication.encoding.DjangoPasswordEncoder" />
 
 <bean id="userDetailsService"
		class="org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl">
		<property name="dataSource">
			<ref local="dataSource" />
		</property>
		<property name="authoritiesByUsernameQuery">
			<value>
				<![CDATA[SELECT format('%s', ?) as username, 'ROLE_USER' as authority;]]>
			</value>
		</property>
		<property name="usersByUsernameQuery">
			<value>
				<![CDATA[SELECT username, password, is_active FROM auth_user WHERE email = ? ORDER BY email]]>
			</value>
		</property>
	</bean>
```