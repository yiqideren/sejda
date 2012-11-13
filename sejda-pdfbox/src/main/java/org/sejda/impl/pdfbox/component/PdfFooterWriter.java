/*
 * Created on 13/nov/2012
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.impl.pdfbox.component;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.sejda.impl.pdfbox.util.FontUtils.getStandardType1Font;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.sejda.model.exception.TaskIOException;
import org.sejda.model.parameter.SetFooterParameters;

/**
 * Component providing footer related functionalities.
 * 
 * @author Andrea Vacondio
 * 
 */
public class PdfFooterWriter implements Closeable {

    private PDDocumentHandler documentHandler;

    /**
     * @param documentHandler
     *            the document handler holding the document where we want to write the footer
     */
    public PdfFooterWriter(PDDocumentHandler documentHandler) {
        this.documentHandler = documentHandler;
    }

    public void writeFooter(SetFooterParameters parameters) throws TaskIOException {
        for (int pageNumber = 1; pageNumber <= documentHandler.getNumberOfPages(); pageNumber++) {
            String label = parameters.formatLabelFor(pageNumber);
            if (label != null) {
                PDFont font = defaultIfNull(getStandardType1Font(parameters.getFont()), PDType1Font.HELVETICA);
                float fontSize = 10.0f;

                PDPage page = documentHandler.getPage(pageNumber);
                PDRectangle pageSize = page.findMediaBox();
                try {
                    float stringWidth = font.getStringWidth(label);
                    float centeredPosition = (pageSize.getWidth() - (stringWidth * fontSize) / 1000f) / 2f;
                    PDPageContentStream contentStream = new PDPageContentStream(
                            documentHandler.getUnderlyingPDDocument(), page, true, true);
                    contentStream.beginText();
                    contentStream.setFont(font, fontSize);
                    contentStream.moveTextPositionByAmount(centeredPosition, 30);
                    contentStream.drawString(label);
                    contentStream.endText();
                    contentStream.close();
                } catch (IOException e) {
                    throw new TaskIOException("An error occurred writing the footer of the page.", e);
                }
            }
        }

    }

    public void close() {
        IOUtils.closeQuietly(documentHandler);
    }
}
