<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
	version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	exclude-result-prefixes="xsl xs"
>
	
	<xsl:output
		method="xml"
		version="1.0"
		encoding="UTF-8"
		omit-xml-declaration="no"
		standalone="no"
		indent="yes"
		media-type="text/xml"/> 
	
	<xsl:template match="/">
		<timeslots>		
			<xsl:apply-templates select="event/timeslots" />
		</timeslots>				
	</xsl:template>
	
	<xsl:template match="timeslots">
		<xsl:apply-templates select="*[1]" />
	</xsl:template>
	
	<xsl:template match="timeslot">
		
		<!-- Determine how many instances of this timeslot remain to be created -->
		<xsl:param name="repeat" select="if (@repeat) then @repeat else 0" />
		
		<!-- Determine the start of this slot -->
		<xsl:param name="start" select="@start" />
		
		<!-- Determine the end of this slot -->
		<xsl:variable name="end">
			<xsl:choose>
				<xsl:when test="@duration and @end">
					<xsl:value-of select="@end" />
				</xsl:when> 
				<xsl:when test="@duration">
					<xsl:value-of select="xs:dateTime($start) + xs:dayTimeDuration(@duration)" />
				</xsl:when>
				<xsl:when test="@end">
					<xsl:value-of select="@end" />
				</xsl:when>				
				<xsl:otherwise>
					<xsl:value-of select="following-sibling::*/@start" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<!-- Create this slot -->
		<timeslot start="{$start}" end="{$end}" />

		<!-- Calculate how many more times this timeslot should be repeated -->
		<xsl:variable name="next-repeat" select="$repeat - 1" />

		<!-- Calculate the start of the next timeslot -->
		<xsl:variable name="next-start">
			<xsl:choose>
				<xsl:when test="$next-repeat &lt; 1">
					<xsl:choose>
						<xsl:when test="following-sibling::*[1]/@start[. != '']">
							<xsl:value-of select="following-sibling::*[1]/@start" />
						</xsl:when>
						<xsl:when test="@break and not(@repeat)">
							<xsl:value-of select="xs:dateTime($end) + xs:dayTimeDuration(@break)" />
						</xsl:when>						
						<xsl:otherwise>
							<xsl:value-of select="$end" />
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="@break">
					<xsl:value-of select="xs:dateTime($end) + xs:dayTimeDuration(@break)" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$end" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>			

		<!-- Create the next timeslot -->
		<xsl:choose>
			<xsl:when test="$next-repeat &gt; 0">
				<xsl:apply-templates select="current()">
					<xsl:with-param name="start" select="$next-start" />			
					<xsl:with-param name="repeat" select="$next-repeat" />			
				</xsl:apply-templates>		
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select="following-sibling::*[1]">
					<xsl:with-param name="start" select="$next-start" />						
				</xsl:apply-templates>
			</xsl:otherwise>		
		</xsl:choose>
		
	</xsl:template>
	
	<xsl:template match="break">
		<xsl:param name="start" />
		
		<xsl:apply-templates select="following-sibling::*[1]">
			<xsl:with-param name="start" select="if ($start != '') then xs:dateTime($start) + xs:dayTimeDuration(@duration) else $start" />
		</xsl:apply-templates>		
	</xsl:template>
	
</xsl:stylesheet>