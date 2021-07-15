/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entities.Contract;
import entities.Installment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

/**
 *
 * @author rafae
 */

/*
Uma empresa deseja automatizar o processamento de seus contratos. O processamento de
um contrato consiste em gerar as parcelas a serem pagas para aquele contrato, com base no
número de meses desejado.
A empresa utiliza um serviço de pagamento online para realizar o pagamento das parcelas.
Os serviços de pagamento online tipicamente cobram um juro mensal, bem como uma taxa
por pagamento. Por enquanto, o serviço contratado pela empresa é o do Paypal, que aplica
juros simples de 1% a cada parcela, mais uma taxa de pagamento de 2%.
Fazer um programa para ler os dados de um contrato (número do contrato, data do contrato,
e valor total do contrato). Em seguida, o programa deve ler o número de meses para
parcelamento do contrato, e daí gerar os registros de parcelas a serem pagas (data e valor),
sendo a primeira parcela a ser paga um mês após a data do contrato, a segunda parcela dois
meses após o contrato e assim por diante. Mostrar os dados das parcelas na tela.
*/

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data");
        System.out.println("Number: ");
        int number = sc.nextInt();

        System.out.println("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());

        System.out.println("Contract value: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.println("Enter number of installments:");
        int N = sc.nextInt();

        ContractService cs = new ContractService(new PaypalService());

        cs.processContract(contract, N);

        System.out.println("Installments:");
        for (Installment x : contract.getInstallments()) {
            System.out.println(x);
        }

        sc.close();
    }
}
