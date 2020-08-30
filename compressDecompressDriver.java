public class compressDecompressDriver {
    public static void main(String[] args) throws Exception {
        String fileName = "WarAndPeace.txt";
        String compressedFileName = "compressed"+fileName;
        String deCompressedFileNamE = "de"+compressedFileName;

        HuffmanEncoder hh = new HuffmanEncoder("inputs/"+fileName);

        hh.constructTree();
        hh.buildCodeMap();

        hh.createBitOutput("outputs/"+compressedFileName);
        hh.readBitInput("outputs/"+compressedFileName, "outputs/"+deCompressedFileNamE);

        fileName = "USConstitution.txt";
        compressedFileName = "compressed"+fileName;
        deCompressedFileNamE = "de"+compressedFileName;

       hh = new HuffmanEncoder("inputs/"+fileName);

        hh.constructTree();
        hh.buildCodeMap();

        hh.createBitOutput("outputs/"+compressedFileName);
        hh.readBitInput("outputs/"+compressedFileName, "outputs/"+deCompressedFileNamE);
    }
}
