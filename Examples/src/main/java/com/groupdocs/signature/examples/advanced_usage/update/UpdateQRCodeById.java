package com.groupdocs.signature.examples.advanced_usage.update;


import com.groupdocs.signature.Signature;
import com.groupdocs.signature.domain.UpdateResult;
import com.groupdocs.signature.domain.signatures.BarcodeSignature;
import com.groupdocs.signature.domain.signatures.BaseSignature;
import com.groupdocs.signature.domain.signatures.QrCodeSignature;
import com.groupdocs.signature.examples.Constants;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UpdateQRCodeById {

    /**
     * Update QR-code signature in the document by known SignatureId.
     * SignatureId could be obtained by Search or Sign method.
     */
    public static void run() throws Exception
    {
            System.out.print("\n--------------------------------------------------------------------------------------------------------------------");
            System.out.print("[Example Advanced Usage] # UpdateBarcodeById : Update Barcode signature in the document by known SignatureId\n");

            // The path to the documents directory.
            String filePath = Constants.SAMPLE_DOCX_BARCODE_SIGNED;
            String fileName = Paths.get(filePath).getFileName().toString();
            String outputFilePath = new File(Constants.OutputPath, "UpdateQRCodeById\\"+ fileName).getPath();
            Constants.checkDir(outputFilePath);
            IOUtils.copy(new FileInputStream(filePath), new FileOutputStream(outputFilePath, true));
            // initialize Signature instance
            Signature signature = new Signature(outputFilePath);
            // read from some data source signature Id value
            String[] signatureIdList = new String[]
                    {
                            "b0123987-b0d4-4004-86ec-30ab5c41ac7e"
                    };
            // create list of Barcode Signature by known SignatureId
            List<BaseSignature> signatures = new ArrayList<BaseSignature>();
            for (String item : signatureIdList)
            {
                QrCodeSignature temp = new QrCodeSignature(item);
                temp.setWidth(150);
                temp.setHeight(150);
                temp.setLeft(200);
                temp.setTop(200);
                signatures.add(temp);
            }
            // update all found signatures
            UpdateResult updateResult = signature.update(outputFilePath,signatures);
            if (updateResult.getSucceeded().size() == signatures.size())
            {
                System.out.print("\nAll signatures were successfully updated!");
            }
            else
            {
                System.out.print("Successfully updated signatures : "+updateResult.getSucceeded().size());
                System.out.print("Not updated signatures : "+updateResult.getFailed().size());
            }
            System.out.print("List of updated signatures:");
            for (BaseSignature temp : updateResult.getSucceeded())
            {
                System.out.print("Signature# Id:"+temp.getSignatureId()+", Location: "+temp.getLeft()+"x"+temp.getTop()+". Size: "+temp.getWidth()+"x"+temp.getHeight());
            }
        }
}