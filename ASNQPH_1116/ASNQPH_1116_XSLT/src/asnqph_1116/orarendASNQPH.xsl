<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>ASNQPH Órarend – 2022/23. I. félév.</h2>
				
				<table border = "4">
					<tr bgcolor = "#9acd32">
						<th>ID</th>
						<th>Tipus</th>
						<th>Targy</th>
						<th>Nap</th>
						<th>Tol</th>
						<th>Ig</th>
						<th>Helyszin</th>
						<th>Oktato</th>
						<th>Szak</th>
					</tr>
					
					<xsl:for-each select = "ASNQPH_Orarend/ora">
						<tr>
							<td><xsl:value-of select="@id"></xsl:value-of></td>
							<td><xsl:value-of select="@tipus"></xsl:value-of></td>
							<td><xsl:value-of select="targy"></xsl:value-of></td>
							<td><xsl:value-of select="idopont/nap"></xsl:value-of></td>
							<td><xsl:value-of select="idopont/tol"></xsl:value-of></td>
							<td><xsl:value-of select="idopont/ig"></xsl:value-of></td>
							<td><xsl:value-of select="helyszin"></xsl:value-of></td>
							<td><xsl:value-of select="oktato"></xsl:value-of></td>
							<td><xsl:value-of select="szak"></xsl:value-of></td>
						</tr>
					</xsl:for-each>>
					
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>