# SpringBoot-ELK

<p>While developing an application, we always incorporate a feature in our code that can direct us what went wrong if the application fails to run normally. This feature is nothing but logging. The more work you do with logging, the less work you have to do with fixing the application issue. We keep all logging information in a text format file, called a log file. This file captures all the details such as startup of a server, all user activities, names of classes, methods, timestamp, occurred exceptions with stack trace etc. Moreover, it depends on us what all information we require in the log file. Sometimes, these log files become larger in size and finding the exact issue manually becomes tedious. Here ELK Stack helps us in analyzing our log files at runtime. Hence, our article is on ‘How to monitor Spring Boot Microservices using ELK Stack?’.</p>

<p>The term ‘ELK Stack’ is becoming more popular day by day. ELK is an acronym of a combination of three tools: Elasticsearch, Logstash and Kibana. Generally, we use all of them to monitor our application. However, each of them has a different purpose that we will discuss in below sections. ELK Stack and Splunk are the world’s most popular log management platform. Here, we will discuss about ELK Stack. Let’s start discussing our topic ‘How to monitor Spring Boot Microservices using ELK Stack?’ and its related concepts.</p>


<h1>What is ELK?</h1>
<p>ELK Stack is a log management platform. The word “ELK” is the acronym for three open source projects: Elasticsearch, Logstash, and Kibana all developed, managed and maintained by Elastic. Furthermore, Elasticsearch is a search and analytics engine, based on the Apache Lucene search engine. Logstash is a server‑side data processing pipeline that ingests data from multiple sources simultaneously, transforms it, and then sends it to a “stash” like Elasticsearch. Kibana is a visualization layer that works on top of Elasticsearch, providing users with the ability to analyze and visualize the data.</p>
<ul>
  <li>E for ElasticSearch</li>
  <li>L for Logstash</li>
  <li>K for Kibana</li>
</ul>

<h3>What is ElasticSearch:</h3>
  <p>ElasticSearch is a NoSQL database that is based on Lucene search engine which will help us to store inputs/logs.</p>
<h3>What is Logstash:</h3>
  <p>Logstash is a log pipe tool that accepts a input/logs from various sources & export that data to various targets.
      Logstash is helps us to process the data   
  </p>
<h3>What is Kibana:</h3>
   <p>Kibana is visualization UI layer, which will helps developers to monitor application logs. Kibana whill helps us to view the data.</p>
      

<h3>ELK Stack Architecture</h3>
<p>Logstash processes the application log files based on the filter criteria we set and sends those logs to Elasticsearch. Through Kibana, we view and analyze those logs when required.</p>

ELK_image.jpg

<p> However, one more component is needed or Data collection called Beats. This led Elastic to rename ELK as the Elastic Stack. </p>

beat image

<p>While dealing with very large amounts of data, you may need Kafka, RabbitMQ for buffering and resilience. For security, nginx can be used.</p>

redis image

<h3>ELK stack configuration</h3>
<p> All these three tools are based on JVM and before start installing them, please verify that JDK has been properly configured. Check that standard JDK 1.8 installation, JAVA_HOME and PATH set up is already done.</p>

<h4>Elasticsearch</h4>
<ol>
  <li>Go to https://www.elastic.co/downloads/elasticsearch</li>
  <li>Select an OS based link</li>
  <li>Extract ZIP file to a location in your system</li>
  <li>To start it, go to the bin folder and run below command, It will start on port : 9200</li>
  <li>elasticsearch.bat</li>
</ol>
<h4>Kibana</h4>
<ol>
  <li>Go to https://www.elastic.co/downloads/kibana</li>
  <li>Select an OS based link</li>
  <li>Extract ZIP file to a location in your system</li>
  <li>Link Kibana with Elasticsearch : Open kibana.yml file from config/kibana.yml : uncomment line elasticsearch.hosts : http://localhost:9200</li>
  <li>To start it, go to the bin folder and run below command, It will start on port : 5601</li>
  <li> > kibana.bat</li>  
</ol>
<h4>Logstash</h4>
<ol>
  <li> Go to https://www.elastic.co/downloads/logstash </li>
  <li> Select an OS based link </li>
  <li> Extract ZIP file to a location in your system </li>
  <li> Go to bin folder and create one file ‘logstash.conf’ with some configuration. Some examples of this file are given in below link.
    https://www.elastic.co/guide/en/logstash/current/config-examples.html </li>
  <li> To start it, go to the bin folder and run below command </li>
  <li> > logstash -f logstash.conf </li>
</ol>

<p> Post installation of ELK components, implement microservice and specify the name and location of the log file to be created in the <B>application.properties</b> file</p>
<b>logging.file=C:/elk/spring-boot-elk.log</b>

<p>Next we will configure the logstash pipeline. When using the ELK stack we are ingesting the data to elasticsearch, the data is initially unstructured. We first need to break the data into structured format and then ingest it to elasticsearch. Such data can then be later used for analysis. This data manipualation of unstructured data to structured is done by Logstash.</p>

image

<p>This is done using the logstash.conf-</p>
input {</br>
  file {</br>
    type => "java"</br>
    path => "C:/elk/spring-boot-elk.log"</br>
    codec => multiline {</br>
      pattern => "^%{YEAR}-%{MONTHNUM}-%{MONTHDAY} %{TIME}.*"</br>
      negate => "true"</br>
      what => "previous"</br>
    }</br>
  }</br>
}</br>
 
filter {</br>
  #If log line contains tab character followed by 'at' then we will tag that entry as stacktrace</br>
  if [message] =~ "\tat" {</br>
    grok {</br>
      match => ["message", "^(\tat)"]</br>
      add_tag => ["stacktrace"]</br>
    }</br>
  }</br>
 
}</br>
 
output {</br>
   
  stdout {</br>
    codec => rubydebug</br>
  }</br>
 
<p>Start logstash using the command prompt as follows-</p>
<p>logstash -f logstash.conf</p>

<p> Start the spring boot project, the log file will be generated in specified path </p>
<p> go to kibana UI console- localhost and create an index pattern logstash-* to see the indexed data-</p>

