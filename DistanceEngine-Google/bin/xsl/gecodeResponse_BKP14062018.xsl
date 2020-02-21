<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:template match="/">
		<geoCodeResponse>

			<xsl:choose>
				<xsl:when test="GeocodeResponse/status = 'OK'">
					<xsl:for-each select="GeocodeResponse/result">
						<result>
							<status>
								<xsl:value-of select="'OK'" />
							</status>
							<formattedAddress>
								<xsl:value-of select="formatted_address" />
							</formattedAddress>
							<countryCode>
								<xsl:value-of select="address_component[type='country']/short_name" />
							</countryCode>
							<xsl:choose>

								<xsl:when
									test="address_component[type='postal_code']/short_name != ''">
									<postalCode>
										<xsl:value-of
											select="address_component[type='postal_code']/short_name" />
									</postalCode>
								</xsl:when>
								<xsl:otherwise>
									<postalCode>
										<xsl:value-of select="'XX'" />
									</postalCode>
								</xsl:otherwise>
							</xsl:choose>
							<province>
								<xsl:value-of
									select="address_component[type='administrative_area_level_1']/short_name" />
							</province>
							<lat>
								<xsl:value-of select="geometry/location/lat" />
							</lat>
							<lon>
								<xsl:value-of select="geometry/location/lng" />
							</lon>
							<xsl:choose>

								<xsl:when test="address_component[type='locality']/short_name != ''">
									<city>
										<xsl:value-of select="address_component[type='locality']/short_name" />
									</city>
								</xsl:when>
								<xsl:otherwise>
									<city>
										<xsl:value-of select="'XX'" />
									</city>
								</xsl:otherwise>
							</xsl:choose>
						</result>
					</xsl:for-each>
				</xsl:when>
				<xsl:otherwise>
					<result>
						<status>
							<xsl:value-of select="GeocodeResponse/status" />
						</status>
					</result>
				</xsl:otherwise>
			</xsl:choose>
		</geoCodeResponse>
	</xsl:template>
</xsl:stylesheet>