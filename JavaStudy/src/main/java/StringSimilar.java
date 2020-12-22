import java.util.Optional;

/**
 * @author: Luo
 * @description:
 * @time: 2020/11/25 20:09
 * Modified By:
 */
public class StringSimilar {
    public int ld(String source, String target) {
        Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source"));
        Optional.ofNullable(target).orElseThrow(() -> new IllegalArgumentException("target"));
        int sl = source.length();
        int tl = target.length();
        // 定义矩阵,行列都要加1
        int[][] matrix = new int[sl + 1][tl + 1];
        // 首行首列赋值
        for (int k = 0; k <= sl; k++) {
            matrix[k][0] = k;
        }
        for (int k = 0; k <= tl; k++) {
            matrix[0][k] = k;
        }
        // 定义临时的编辑消耗
        int cost;
        for (int i = 1; i <= sl; i++) {
            for (int j = 1; j <= tl; j++) {
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                matrix[i][j] = min(
                        // 左上
                        matrix[i - 1][j - 1] + cost,
                        // 右上
                        matrix[i][j - 1] + 1,
                        // 左边
                        matrix[i - 1][j] + 1
                );
            }
        }
        return matrix[sl][tl];
    }

    private int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
    public static void main(String[] args) {
        StringSimilar stringSimilar = new StringSimilar();
        System.out.println(stringSimilar.ld("渝D2T073","翼D3T073"));
    }
}
