package com.groupdocs.signature.examples.basic_usage.sign.metadata;


import com.groupdocs.signature.Signature;
import com.groupdocs.signature.domain.signatures.metadata.SpreadsheetMetadataSignature;
import com.groupdocs.signature.examples.Constants;
import com.groupdocs.signature.exception.GroupDocsSignatureException;
import com.groupdocs.signature.options.sign.MetadataSignOptions;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

public class SignSpreadsheetWithMetadata {
    /**
     * Sign spreadsheets document with metadata signature
     */
    public static void run()
    {
        // The path to the documents directory.
        String filePath = Constants.SAMPLE_SPREADSHEET;
        String fileName = Paths.get(filePath).getFileName().toString();

        String outputFilePath = new File(Constants.OutputPath, "SignSpreadsheetWithMetadata//"+ fileName).getPath();

        try {
            Signature signature = new Signature(filePath);
            // setup options with text of signature
            MetadataSignOptions options = new MetadataSignOptions();

            // Create few Spreadsheet Metadata signatures
            SpreadsheetMetadataSignature[] signatures = new SpreadsheetMetadataSignature[]
                    {
                            new SpreadsheetMetadataSignature("Author", "Mr.Scherlock Holmes"),
                            new SpreadsheetMetadataSignature("DateCreated", new Date()),
                            new SpreadsheetMetadataSignature("DocumentId", 123456),
                            new SpreadsheetMetadataSignature("SignatureId", 123.456)
                    };
            options.getSignatures().addRange(signatures);

            // sign document to file
            signature.sign(outputFilePath, options);
            System.out.print("\nSource document signed successfully.\nFile saved at " + outputFilePath);
        }catch(Exception e){
            throw new GroupDocsSignatureException(e.getMessage());
        }
    }
}