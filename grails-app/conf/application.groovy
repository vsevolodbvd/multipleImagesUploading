grails {
	profile = 'web'
	codegen {
		defaultPackage= 'imageUploading'
	}
	spring {
		transactionManagement {
			proxies = false
		}
	}
}
info {
	app {
		name: 'ImageUploadingApp'
		version: '0.1'
		grailsVersion: '3.2.0'
	}
}

spring.groovy.template['check-template-location'] = false

// Spring Actuator Endpoints are Disabled by Default

endpoints {
	enabled = false
	jmx {
		enabled = true
	}
}
grails {
	urlmapping.cache.maxsize = 1000
	controllers.defaultScope = 'singleton'

	views {
		'default' {codec = 'none'} // none, html, base64
		gsp{
			encoding = 'UTF-8'
			htmlcodec = 'xml'
			codecs {
				expression= 'none'
				scriplets= 'none'
				taglib = 'none'
				staticparts = 'none'
			}
		}
	}
}

endpoints.jmx.'unique-name' = true

grails.mime.file.extensions = false // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
		all          : '*/*',
		atom         : 'application/atom+xml',
		css          : 'text/css',
		csv          : 'text/csv',
		form         : 'application/x-www-form-urlencoded',
		html         : ['text/html', 'application/xhtml+xml'],
		js           : 'text/javascript',
		json         : ['application/json', 'text/json'],
		multipartForm: 'multipart/form-data',
		rss          : 'application/rss+xml',
		text         : 'text/plain',
		xml          : ['text/xml', 'application/xml']
]

grails.hibernate.cache.queries = false

hibernate {
	cache.use_second_level_cache = false
	cache.use_query_cache = false
	cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'
}

grails.app.context = '/'

transloadit {
	api {
		key = '3867c2f0aa3d11e6bef91f8bd695c885'
		secret = '45d56907ae6779b60c1f4c0c3c1d14408dd49aea'
	}
	templates {
		imagesUpload = '2dd62c10abe511e69323bdb9e726731f'
	}
}

dataSource {
	pooled = true

	properties {
		/** The maximum number of active connections that can be allocated from
		 * this pool at the same time, or zero for no limit.
		 * Make it '-1' to prevent performance problems with idle connections
		 */
		maxActive = -1
		/**
		 * The maximum number of active connections that can remain idle in the
		 * pool, without extra ones being released, or zero for no limit.
		 */
		maxIdle = 8
		/**
		 * The minimum number of active connections that can remain idle in the
		 * pool, without extra ones being created, or 0 to create none.
		 */
		minIdle = 1
		/**
		 * The maximum number of milliseconds that the pool will wait (when there
		 * are no available connections) for a connection to be returned before
		 * throwing an exception, or -1 to wait indefinitely.
		 */
		maxWait = 10000
		initialSize = 1
		minEvictableIdleTimeMillis = 1000 * 60
		timeBetweenEvictionRunsMillis = 1000 * 3
		numTestsPerEvictionRun = 10
		testOnBorrow = true
		testWhileIdle = true
		testOnReturn = false
		validationQuery = "SELECT 1"
	}

	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
	username = "root"
	password = "mysql"
	dbCreate = 'none' //just to ensure that nothing will be launched!
}

environments {
	development {
		server.port = '8080'
		server.host = "localhost:${server.port}"
		grails.serverURL = "localhost:${server.port}"
		grails.logging.jul.usebridge = true

		dataSource {
			url = "jdbc:mysql://localhost:3306/testbase?autoReconnect=true&useUnicode=true&characterEncoding=utf8"
			cache.use_second_level_cache = false
			cache.use_query_cache = false
		}
	}
}