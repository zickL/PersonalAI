package org.example.generated;


// Use the fluent-style API to construct the code generator configuration
import org.jooq.meta.jaxb.*;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.codegen.GenerationTool;


public class Generate {


    public static void main(String[] args) throws Exception {


// [...]


        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl("jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres")
                        .withUser("postgres.llvoxefofzazghdrhgwy")
                        .withPassword("zickyLEE1996@"))
                .withGenerator(new Generator()
                        .withGenerate(new org.jooq.meta.jaxb.Generate().withComments(false))
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("public"))
                        .withTarget(new Target()
                                .withPackageName("org.example.generated")
                                .withDirectory("/Users/zickyli/Desktop/chat-project/src/main/java")));


        GenerationTool.generate(configuration);
    }
}
